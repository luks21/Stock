package stock.Servicios;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import stock.Entidades.Producto;
import stock.exceptions.NonexistentEntityException;
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("StockNatural");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto = em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (EncontProductos(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> EncontProductos() {
        return EncontProductos(true, -1, -1);
    }

    public List<Producto> EncontProductos(int maxResults, int firstResult) {
        return EncontProductos(false, maxResults, firstResult);
    }

    private List<Producto> EncontProductos(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto EncontProductos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Producto> EncontProductos(String nombre) {
        EntityManager em = getEntityManager();
        try {
            List<Producto> resBusq = em.createNativeQuery("SELECT * FROM STOCK.PRODUCTO").getResultList();
            return resBusq;
        } finally {
            em.close();
        }
    }
    public int contProd() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
public List<Producto> listarFiltro(String filtro){
    EntityManager em = getEntityManager();
    List <Producto> prodFilt = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre").setParameter("nombre", filtro).getResultList();
    return prodFilt;
}
public List<Producto> listarFiltroCant(int cantidad){
    EntityManager em = getEntityManager();
    List <Producto> prodFilt = em.createQuery("SELECT p FROM Producto p WHERE p.cantidad <= :cantidad").setParameter("cantidad", cantidad).getResultList();
    return prodFilt;
}
}