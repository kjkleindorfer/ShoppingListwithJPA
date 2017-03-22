package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

public class ListItemHelper{
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ShoppingListwithJPA");
	
	
	public void cleanUp(){
		emfactory.close();
	}
	
	public void deleteItem(ListItem toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.store = :selectedStore and li.item = :selectedItem", ListItem.class);
		typedQuery.setParameter("selectedStore", toDelete.getStore());
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		typedQuery.setMaxResults(1);
		ListItem result = typedQuery.getSingleResult();
		em.remove(result);
		
		em.getTransaction().commit();
		em.close();
		
	}
	

	
	public void insertItem(ListItem li){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItem> searchForItemByItem(String itemName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.item = :selectedItem", ListItem.class);
		typedQuery.setParameter("selectedItem", itemName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListItem> searchForItemByStore(String storeName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.store = :selectedStore", ListItem.class);
		typedQuery.setParameter("selectedStore", storeName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public ListItem searchForItemById(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, id);
		em.close();
		return found;
	}

	public List<ListItem> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li", ListItem.class);
		List<ListItem> allItems = typedQuery.getResultList();
		em.close();
		return allItems;
	}

	public void updateItem(ListItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

}
