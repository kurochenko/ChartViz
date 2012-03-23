package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.entity.Chart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Repository
public class HibernateChartDAO implements ChartDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(Chart chart) {
        em.persist(chart);
    }

    @Override
    public void edit(Chart chart) {
        em.merge(chart);
    }

    @Override
    public void remove(Chart chart) {
        em.remove(chart);
    }

    @Override
    public Chart find(Long id) {
        return em.find(Chart.class, id);
    }

    @Override
    public List<Chart> findAll() {
        Query q = em.createQuery("select c from Chart c");
        return q.getResultList();
    }
}
