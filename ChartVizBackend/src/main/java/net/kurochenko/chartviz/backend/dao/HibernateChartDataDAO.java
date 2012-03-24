package net.kurochenko.chartviz.backend.dao;

import net.kurochenko.chartviz.backend.entity.Chart;
import net.kurochenko.chartviz.backend.entity.ChartData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Repository
public class HibernateChartDataDAO implements ChartDataDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(ChartData data) {
        em.persist(data);
    }

    @Override
    public ChartData find(Long id) {
        return em.find(ChartData.class, id);
    }

    @Override
    public void removeByChart(Chart chart) {
        Query q = em.createQuery("delete from ChartData d where d.chart = :chart");
        q.setParameter("chart", chart);
        q.executeUpdate();
    }
    
    @Override
    public List<ChartData> findAll(Chart chart) {
        Query q = em.createQuery("from ChartData d where d.chart = :chart order by d.time");
        q.setParameter("chart", chart);
        return q.getResultList();
    }

    @Override
    public List<ChartData> findRange(Chart chart, Date from, Date to) {
        Query q = em.createQuery("from ChartData d where d.chart = :chart and d.time >= :from and d.time <= :to order by d.time");
        q.setParameter("chart", chart)
         .setParameter("from", from)
         .setParameter("to", to);

        return q.getResultList();
    }    
}
