package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Classe JpaTest. Classe principale du programme.
 * 
 * @author Jeremy Cavron
 * @version 1.0
 *
 */
public class JpaTest {

  // --- Déclaration des propriétées ---
  private EntityManager manager;
  private static long start;

  /**
   * Constructeur de la classe.
   * 
   * @param manager
   *          : POJO qui permet d'encapsuler les données des occurrences des
   *          tables.
   */
  public JpaTest(EntityManager manager) {
    this.manager = manager;
  }

  /**
   * Procédure principale de la classe.
   * 
   * @param args
   *          : Arguments
   */
  public static void main(String[] args) {
    start = System.currentTimeMillis();
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
    EntityManager manager = factory.createEntityManager();
    JpaTest test = new JpaTest(manager);
    EntityTransaction tx = manager.getTransaction();
    tx.begin();
    try {
      test.createEntities();
    } catch (Exception exeptCreateEntities) {
      exeptCreateEntities.printStackTrace();
    }
    tx.commit();
    test.listPersonnes();
    manager.close();
    long end = System.currentTimeMillis();
    long duree = end - start;

    System.out.println(".. ok");
    System.err.println("temps d'exec = " + duree + " ms");
  }

  /**
   * Procédure qui permet de créer les entités pour la base de données.
   */
  private void createEntities() {
    int numPersonne = manager.createQuery("Select a From Personne a", Personne.class)
        .getResultList().size();

    if (numPersonne == 0) {

      // Création des résidences, chauffages, ajout équipement électronique
      //Model Google checkstyle
      Residence residence1 = new Residence(2000, 10);
      residence1.addChauffage(new Chauffage());
      residence1.addChauffage(new Chauffage());
      residence1.addChauffage(new Chauffage());
      residence1.addChauffage(new Chauffage());
      residence1.addEquipementElec(new EquipementElec());
      residence1.addEquipementElec(new EquipementElec());
      residence1.addEquipementElec(new EquipementElec());
      residence1.addEquipementElec(new EquipementElec());
      residence1.addEquipementElec(new EquipementElec());
      residence1.addEquipementElec(new EquipementElec());
      Residence residence2 = new Residence(1000, 5);
      residence2.addChauffage(new Chauffage());
      residence2.addChauffage(new Chauffage());
      residence2.addChauffage(new Chauffage());
      residence2.addChauffage(new Chauffage());
      residence2.addChauffage(new Chauffage());
      residence2.addEquipementElec(new EquipementElec());
      residence2.addEquipementElec(new EquipementElec());
      residence2.addEquipementElec(new EquipementElec());
      Residence residence3 = new Residence(1500, 8);
      residence3.addChauffage(new Chauffage());
      residence3.addChauffage(new Chauffage());
      residence3.addEquipementElec(new EquipementElec());
      residence3.addEquipementElec(new EquipementElec());
      Residence residence4 = new Residence(3000, 9);
      residence4.addChauffage(new Chauffage());
      residence4.addChauffage(new Chauffage());
      residence4.addChauffage(new Chauffage());
      Residence residence5 = new Residence(3000, 9);
      residence5.addChauffage(new Chauffage());
      residence5.addChauffage(new Chauffage());
      Residence residence10 = new Residence(3000, 9);
      residence10.addEquipementElec(new EquipementElec());
      residence10.addEquipementElec(new EquipementElec());
      residence10.addEquipementElec(new EquipementElec());

      //Création de personnes, d'amis et affectation aux résidences
      Personne p1 = new Personne("Jaka", "Bada", "jaka.bada@breizh.bzh");
      Personne p2 = new Personne("Durand", "Henry", "durand.henry@breizh.bzh");
      Personne p3 = new Personne("Captain", "Nemo", "captain.nemo@breiah.bzh");
      Personne p4 = new Personne("Sarkozi", "Nicolas", "sarkozi.nicolas@orange.fr");
      Personne p5 = new Personne("Macron", "Emmanuel", "macron.emmanuel@gmail.fr");
      Personne p6 = new Personne("Dupont", "Alain", "dupont.alain@gmail.com");
      Personne p7 = new Personne("Hollande", "François", "hollande.francois@sfr.fr");
      
      p1.addAmi(p2);
      p1.addAmi(p4);
      p4.addAmi(p5);
      p2.addAmi(p6);
      p2.addAmi(p7);
      

      //Ajout de résidence. Model Google CheckStyle
      Residence residence6 = new Residence(3000, 9);
      Residence residence7 = new Residence(3000, 9);
      Residence residence8 = new Residence(3000, 9);
      Residence residence9 = new Residence(3000, 9);
      
      // Ajout des résidences aux personnes
      p1.addResidence(residence1);
      p1.addResidence(residence2);
      p2.addResidence(residence3);
      p2.addResidence(residence9);
      p3.addResidence(residence4);
      p4.addResidence(residence5);
      p5.addResidence(residence6);
      p5.addResidence(residence10);
      p6.addResidence(residence7);
      p7.addResidence(residence8);

      manager.persist(p1);
      manager.persist(p2);
      manager.persist(p3);
      manager.persist(p4);
      manager.persist(p5);
      manager.persist(p6);
      manager.persist(p7);
    }
  }

  /**
   * 
   * Procédure qui affiche les personnes.
   * 
   */
  private void listPersonnes() {
    List<Personne> resultList = manager.createQuery("Select a From Personne a", Personne.class)
        .getResultList();
    System.out.println("num of personne:" + resultList.size());
    for (Personne next : resultList) {
      System.out.println("next personne: " + next);
    }
  }
}
