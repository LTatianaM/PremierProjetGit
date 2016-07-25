package org.formation.Service;

import java.util.List;

import org.formation.DAO.AlimentDBUtil;
import org.formation.model.Aliment;

public class AlimentService {

	public List<Aliment> getAliments() throws Exception {
		return AlimentDBUtil.getInstance().getAliments();

	}

	public void addAliment(Aliment aliment) throws Exception {

		for (Aliment al : AlimentDBUtil.getInstance().getAliments()) {

			if (al.getNom().toUpperCase().equals(aliment.getNom().toUpperCase())) {

				System.out.println("le nom existe déjà");
				return;
			}
		}

		AlimentDBUtil.getInstance().addAliment(aliment);

	}

	public Aliment getAliment(int monId) throws Exception {
		return AlimentDBUtil.getInstance().getAliment(monId);

	}

/*	public void updateAliment(int monId, String nom, String categorie, String couleur) throws Exception {
		AlimentDBUtil.getInstance().updateAliment(monId, nom, categorie, couleur);
	}*/
	
	public void updateAliment(Aliment aliment) throws Exception {
		AlimentDBUtil.getInstance().updateAliment(aliment);
	}

	public void delAliment(int monId) throws Exception {
		AlimentDBUtil.getInstance().delAliment(monId);
	}

}
