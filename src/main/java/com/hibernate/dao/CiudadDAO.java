package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;

/* 3 CREACIÓN DE LA CLASE DAO. Clase Data Access Object */
/* Operaciones básicas para interactuar con la BD */
public class CiudadDAO {

	/* INSERT */
	/*
	 * El objeto persona que vamos a insertar se le pasa como parámetro a la
	 * función. Primero se crea un objeto vacío Transaction. Luego se invoca a la
	 * función estática creada en el apartado anterior, para obtener y abrir una
	 * sesión de acceso a la BD. Luego se inicia la transacción, se llama a persist
	 * con la persona pasada como parámetro para que la guarde en la BD y
	 * confirmamos la transacción con commit. Si salta alguna excepción, la
	 * transacción volverá al estado anterior con el rollback. La función no
	 * devuelve nada
	 */
	public void insertCiudad(Ciudad c) { // Reutilización código - Cambio Ciudad
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(c);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.err.println("Error al insertar la Ciudad: " + e.getMessage());
		}
	}

	/* UPDATE */
	/*
	 * Esta función requiere que le pasemos como parámetro el objeto persona cuyos
	 * datos han cambiado y queremos actualizar. Se crea una sesión, después una
	 * transacción y se invoca a la función merge que será la que actualice la tabla
	 * con la persona pasada como parámetro. Después se confirma la transacción (o
	 * se anula si hubiera alguna excepción). Al igual que la función anterior, esta
	 * función no necesita devolver nada
	 */
	public void updateCiudad(Ciudad c) { // Reutilización código - Cambio Ciudad
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(c);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.err.println("Error al actualizar la Ciudad: " + e.getMessage());
		}
	}

	/* DELETE */
	/*
	 * En este caso, le pasamos a la función el identificador de la persona que
	 * queremos borrar. En primer lugar, se crea la sesión y la transacción y con
	 * get se obtiene la persona con el id especificado. Esa persona será la que se
	 * elimine de la tabla con remove. Finalmente, se confirma o anula la
	 * transacción. Esta función tampoco devuelve nada.
	 */
	public void deleteCiudad(int codigo) { // Reutilización código - Cambio Ciudad / Parámetro que es el mismo de la
											// clase
		Transaction transaction = null;
		Ciudad c = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			c = session.get(Ciudad.class, codigo); // Cógeme de la tabla ciudad la ciudad con código y guardalo en c
			if (c != null) {
				session.remove(c); // Borra la ciudad que hayas seleccionado de la tabla
				transaction.commit(); // Ejecuta la transacción
			} else {
				System.out.println("La ciudad con código " + codigo + " no existe.");
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
	}

	/* SELECT */
	/*
	 * Esta función necesita que se le pase como parámetro el id de la persona que
	 * queremos obtener y devolverá el objeto Persona seleccionado, con todos sus
	 * datos. En primer lugar, se crean la sesión y la transacción y después con get
	 * se obtiene de la tabla el objeto Persona asociado al id especificado. Dicho
	 * objeto será el que devuelva la función. Recuerda que todas estas funciones
	 * definen cómo interactuar con la BD, pero será en el main donde se invoquen y
	 * usen realmente.
	 */
	public Ciudad selectCiudadById(int codigo) { // Reutilización código - Cambio Ciudad / Parámetro que es el mismo de
													// la clase
		Transaction transaction = null;
		Ciudad c = null; // Reutilización código - Cambio Ciudad
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			c = session.get(Ciudad.class, codigo); // Reutilización código - Cambio Ciudad / Parámetro que es el mismo
			if (c != null) {
				transaction.commit();
			} else {
				System.out.println("La ciudad con código " + codigo + " no existe.");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
		return c;
	}

	/* SELECT ALL */
	/*
	 * Esta función no necesita parámetros y devuelve todas las filas de la tabla,
	 * transformándolas en una lista de objetos. En primer lugar, se crean la sesión
	 * y la transacción. Después se crea una consulta o query donde especificamos en
	 * HQL la clase de la cual queremos las filas, y la convertimos a lista con
	 * getResultList. La lista será devuelta por la función. Inicialmente tenemos
	 * que asignarle el valor null para controlar los posibles errores.
	 */
	public List<Ciudad> selectAllCiudads() { // Reutilización código - Cambio Ciudads
		Transaction transaction = null;
		List<Ciudad> ciudades = null; // Reutilización código - Cambio Ciudads
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ciudades = session.createQuery("FROM Ciudad", Ciudad.class).getResultList(); // Reutilización código -
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades; // Reutilización código - Cambio Ciudads
	}

	/*
	 * Muestra ciudades con más de 1 millón de habitantes
	 * 
	 */
	public List<Ciudad> selectAllCiudadsMore1M() { // Reutilización código - Cambio Ciudads
		Transaction transaction = null;
		List<Ciudad> ciudades = null; // Reutilización código - Cambio Ciudads
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ciudades = session.createQuery("FROM Ciudad WHERE numHabi > 1000000", Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades; // Reutilización código - Cambio Ciudads
	}

	/*
	 * Muestra ciudades con menos de los habitantes que le pase el user
	 * 
	 */
	public List<Ciudad> selectAllCiudadsLessHab(int numHabi) { // Reutilización código - Cambio Ciudads
		Transaction transaction = null;
		List<Ciudad> ciudades = null; // Reutilización código - Cambio Ciudads
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Ciudad> query = session.createQuery("FROM Ciudad WHERE numHabi < :numHabiParam", Ciudad.class);
			query.setParameter("numHabiParam", numHabi);
			ciudades = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades; // Reutilización código - Cambio Ciudads
	}

	/*
	 * Muestra ciudad pasándole el nombre
	 */
	public List<Ciudad> selectCiudadByNamee(String nomCiu) { // Reutilización código - Cambio Ciudads
		Transaction transaction = null;
		List<Ciudad> ciudades = null; // Reutilización código - Cambio Ciudads
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Ciudad> query = session.createQuery("FROM Ciudad WHERE nombre = :nombreParam", Ciudad.class); // Ciudad es la clase
			query.setParameter("nombreParam", nomCiu);
			Ciudad c = query.uniqueResult();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades; // Reutilización código - Cambio Ciudads
	}

	public Ciudad selectCiudadByName(String nomCiu) { // Reutilización código - Cambio Ciudad / Parámetro que es el mismo de
		// la clase
		Transaction transaction = null;
		Ciudad c = null; // Reutilización código - Cambio Ciudad
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Ciudad> query = session.createQuery("FROM Ciudad WHERE nombre = :nombreParam", Ciudad.class); // Ciudad es la clase
			query.setParameter("nombreParam", nomCiu);
			c = query.uniqueResult();
			
			if (c == null) {
				System.out.println("La ciudad con código " + nomCiu + " no existe.");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
		return c;
	}
}
