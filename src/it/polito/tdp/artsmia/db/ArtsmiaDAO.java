package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostre;
import it.polito.tdp.artsmia.model.OperePerMostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getAnniMostre() {
		String sql = "select distinct begin " + 
				"from exhibitions " + 
				"order by begin desc ";
		List<Integer> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("begin"));
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Prblema di connessione al database");
		}
	}

	public List<Mostre> getMostreDaAnno(Integer anno) {
		String sql = "SELECT * from exhibitions where begin >= ? ";
		
		List<Mostre> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Mostre mostra = new Mostre(res.getInt("exhibition_id"), res.getString("exhibition_department"),
						res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				result.add(mostra);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Prblema di connessione al database");
		}
	}

	public List<Mostre> getMostreSuccessive(Mostre m) {
		String sql = "select * " + 
				"from exhibitions " + 
				"where  begin <= ? " + 
				"and begin>= ? ";
		
		List<Mostre> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, m.getFine());
			st.setInt(2, m.getInizio());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Mostre mostra = new Mostre(res.getInt("exhibition_id"), res.getString("exhibition_department"),
						res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				result.add(mostra);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Prblema di connessione al database");
		}
	}

	public OperePerMostra getMostraPiuGrande(Integer anno) {
		String sql = "select  e.exhibition_id, e.exhibition_title, count(eo.object_id) as counter " + 
				"from exhibitions as e, exhibition_objects as eo " + 
				"where  e.exhibition_id=eo.exhibition_id " + 
				"and begin >= ? " + 
				"group by  e.exhibition_id " + 
				"order by counter DESC LIMIT 1 ";
		
		OperePerMostra result=null;
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			if(res.next()) {
				result = new OperePerMostra(res.getInt("exhibition_id"),
						res.getString("exhibition_title"), res.getInt("counter"));
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Prblema di connessione al database");
		}
	}

	//inizializzo tutte le mostre con le loro opere (da simulatore)
	public List<Integer> getOggetiDaMostra(Mostre mostra) {
		String sql = "SELECT object_id from exhibition_objects where exhibition_id = ?";
		
		List<Integer> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mostra.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int objectId = res.getInt("object_id");
				result.add(objectId);
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Prblema di connessione al database");
		}
	}
	
	
}
