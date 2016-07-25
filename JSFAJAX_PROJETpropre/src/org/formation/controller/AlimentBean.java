package org.formation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.formation.Service.AlimentService;
import org.formation.model.Aliment;

@ManagedBean(name = "aliBean")
public class AlimentBean {

	// Attributs
	private List<Aliment> listAliment;

	// Appel de la couche service:
	AlimentService service = new AlimentService();

	/*
	 * private java.util.logging.Logger logger =
	 * java.util.logging.Logger.getLogger(getClass().getName());
	 */

	// Constructeur
	public AlimentBean() {
		// TODO Auto-generated constructor stub

		// initialisation de la liste:
		listAliment = new ArrayList<>();
		/*
		 * listAliment.add(new Aliment("Orange", "fruit", "orange"));
		 * listAliment.add(new Aliment("Salade", "legume", "vert"));
		 * listAliment.add(new Aliment("Patate", "feculent", "jaune"));
		 * listAliment.add(new Aliment("poulet", "viande", "beige"));
		 */
	}

	// -------------Appel de la DAO--------------------:

	// listes des aliments
	public List<Aliment> getListAliment() {
		try {

			listAliment = service.getAliments();
			/* listAliment = AlimentDBUtil.getInstance().getAliments(); */
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);
		}
		return listAliment;
	}

	// ajouter un aliment
	public String addAliment(Aliment theAliment) {

		/* logger.info("Adding student: "+ theAliment); */
		try {
			service.addAliment(theAliment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/*
			 * Logger.log(Level.SEVERE, "Error adding aliment",e); addEr(e);
			 */
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);

			/* return null; */
		}
		return "list-aliments?faces-redirect=true";
	}

	// modifier un aliment
	public String updateAliment(Aliment aliment) {
		try {
			service.updateAliment(aliment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);
		}

		return "list-aliments?faces-redirect=true";
	}

	// supprimer un aliment:
	public String delAliment(int id) {
		try {
			service.delAliment(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);
		}

		return "list-aliments?faces-redirect=true";
	}

	public String getMonAliment(int id) {

		try {
			Aliment aliment = service.getAliment(id);

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("aliment", aliment);

		} catch (Exception e) {

			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			throw new ValidatorException(message);

		}

		return "updateAliment.xhtml";
	}
	// ------------------getters et setters-----------------------:

	public void setListAliment(List<Aliment> listAliment) {
		this.listAliment = listAliment;
	}

}
