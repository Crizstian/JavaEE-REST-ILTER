package mx.org.mexlter.eis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import mx.org.mexlter.data.Network;
import mx.org.mexlter.data.Sitio;
import mx.org.mexlter.data.SitiosRequested;
import mx.org.mexlter.data.Variables;
import mx.org.mexlter.data.json.FormBiome;
import mx.org.mexlter.data.json.FormData;
import mx.org.mexlter.funciones.Funciones;

@Stateless
public class SitiosDaoImpl implements SitiosDao {

	String ip = "192.168.99.100";
	String port = "3306";
	String url = "jdbc:mysql://" + ip + ":" + port + "/";
	String dbName = "averages";
	String userName = "root";
	String password = "ecosistemas";

	@Override
	public List<Network> listarTodosLosSitios() {

		String sql = "SELECT DISTINCT r.IdRed as sitio, r.Red as nombre FROM Redes r, Valores v WHERE r.IdRed<>'EJEM' and r.IdRed=v.IdRed order by r.Red";
		List<Network> sitios = new ArrayList<>();
		int i = 0;

		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next())
				sitios.add(new Network(++i, rs.getString("sitio"), rs
						.getString("nombre")));

		} catch (SQLException e) {
			System.out.println("SQLException findAllNetworks " + e);
		}

		return sitios;
	}

	@Override
	public List<Network> buscarSitiosPorContinente(String continente) {

		String sql = "SELECT distinct r.IdRed as sitio, r.Red as nombre FROM Redes r, Valores v WHERE r.IdRed<>'EJEM' and r.IdRed=v.IdRed and r.continente='"
				+ continente + "' order by r.Red";
		List<Network> sitios = new ArrayList<>();
		int i = 0;
		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next())
				sitios.add(new Network(++i, rs.getString("sitio"), rs
						.getString("nombre")));

		} catch (SQLException e) {
			System.out.println("SQLException findNetworkById " + e);
		}

		return sitios;
	}

	@Override
	public List<SitiosRequested> buscarSitiosConsultados(List<FormData> formData) {

		String campos = "SELECT distinct d.IdRed as S1, s.IdSitio as F1, s.IdSubsitio as SU, s.Subsitio as SUN, d.red as N1, s.acronim A, s.Sitio as SS ";
		String from = " FROM Redes d, Sitios s, Valores v "
				+ " WHERE s.IdRed = d.IdRed "
				+ formData.get(formData.size() - 1).getSql();

		String consulta = "", checar = "", sigAnt = "-1";
		int varAnt = -1;

		for (FormData f : formData) {
			if (f.getSql().equals("0")) {
				boolean reconstruye = true;

				if (varAnt != -1 && varAnt == f.getX()) {

					checar += sigAnt == "=" ? "(" + checar + ") OR (" : "("
							+ checar + ") AND (";

					reconstruye = false;
					sigAnt = f.getSignoX();
					checar += " v.IdVariable=" + f.getX()
							+ " AND v.TipoValor='" + f.getTipoX()
							+ "' AND v.Valor " + sigAnt + " " + f.getValorX()
							+ ") ";

				}

				if (reconstruye) {
					if (varAnt != -1) {
						consulta += " AND d.IdRed=v.IdRed AND s.IdSitio=v.IdSitio AND s.IdSubsitio=v.IdSubsitio "
								+ " AND (" + checar + ")";
						
						try (Connection con = DriverManager.getConnection(url
								+ dbName, userName, password);
								PreparedStatement st = con
										.prepareStatement(campos + from
												+ consulta);
								ResultSet rs = st.executeQuery()) {
							checar = "";
							while (rs.next()) {
								checar += checar != "" ? " or " : "";

								checar += " (d.IdRed='" + rs.getString("S1")
										+ "' and s.IdSitio='"
										+ rs.getString("F1")
										+ "' and s.IdSubsitio='"
										+ rs.getString("SU") + "')";
							}
							
							consulta = checar != "" ? " AND (" + checar + ")" : "";

						} catch (SQLException e) {
							System.out
									.println("Error en findQueryNetwork Consulta 1"
											+ e);
						}
					}
					sigAnt = f.getSignoX();
					checar = " v.IdVariable=" + f.getX() + " AND v.TipoValor='"
							+ f.getTipoX() + "' AND v.Valor " + sigAnt + " "
							+ f.getValorX();
					varAnt = f.getX();
				}

			}
		}
		if (varAnt != -1)
			consulta += " AND d.IdRed=v.IdRed AND s.IdSitio=v.IdSitio AND s.IdSubsitio=v.IdSubsitio "
					+ " AND (" + checar + ")";

		return obtenerValoresAverage(campos + from + consulta);
	}

	@Override
	public List<Sitio> buscarSitioPorId(String sitio, String folio) {

		String sql = "select distinct " + "d.Red as network, "
				+ "s.Sitio as name, " + "s.Acronim A, " + "d.Pais country, "
				+ "d.Coordinador chair, " + "s.Coordinador sC, "
				+ "s.Coordinadoremail sCe, " + "s.WEB sW, " + "d.IdRed as S1, "
				+ "s.IdSitio as F1 " + "from Redes d, Sitios s "
				+ "where s.IdRed = d.IdRed and d.IdRed='" + sitio
				+ "' and s.IdSitio=" + folio + " and s.IdSubsitio=" + 0;

		List<Sitio> sitios = new ArrayList<>();
		List<Variables> v1;
		Sitio s = new Sitio();

		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				v1 = new ArrayList<>();
				for (Variables v : listarTodosLasVariables()) {

					String sqls[] = {
							"select valor from Valores where IdRed='" + sitio
									+ "' and IdSitio=" + folio
									+ " and IdSubsitio=" + 0
									+ " and IdVariable='" + v.getId()
									+ "' and TipoValor='Average'",

							"select valor from Valores where IdRed='" + sitio
									+ "' and IdSitio=" + folio
									+ " and IdSubsitio=" + 0
									+ " and IdVariable='" + v.getId()
									+ "' and TipoValor='Minimum'",

							"select valor from Valores where IdRed='" + sitio
									+ "' and IdSitio=" + folio
									+ " and IdSubsitio=" + 0
									+ " and IdVariable='" + v.getId()
									+ "' and TipoValor='Maximum'" },
							tipo = "";
					
					for (int i = 0; i < sqls.length - 1; i++) {
						try (PreparedStatement st2 = con
								.prepareStatement(sqls[i]);
								ResultSet rs2 = st2.executeQuery()) {
							if (i == 0)
								tipo = "Average";
							else if (i == 2)
								tipo = "Minimum";
							else if (i == 3)
								tipo = "Maximum";

							if (rs2.next())
								v1.add(new Variables(Funciones.calcularValor(
										v.getId(), rs2.getString("valor")),
										tipo, v.getId()));
							else
								v1.add(new Variables(" - ", tipo, v.getId()));

						} catch (SQLException e) {
							System.out.println("SQLException findNetworkById "
									+ e);
						}
					}
				}
				String sql5 = "SELECT Eco1, Eco2, Eco3, Class, MAB, RAMSAR, UNESCOWHS, OtherClass  FROM Sitios WHERE IdRed='"
						+ sitio
						+ "' AND IdSitio='"
						+ folio
						+ "' AND IdSubsitio='" + 0 + "'  AND IdSubsitio='0'";

				try (PreparedStatement st3 = con.prepareStatement(sql5);
						ResultSet rs2 = st3.executeQuery()) {
					if (rs2.next()) {
						s.setImportant_ecosystem(rs2.getString("Eco1"));
						s.setSite_type(rs2.getString("Class"));
						String type = "";
						if (rs2.getInt("MAB") == 1)
							type = "Biosphere Reserve (MAB)";
						else if (rs2.getInt("RAMSAR") == 1)
							type = "RAMSAR Site";
						else if (rs2.getInt("UNESCOWHS") == 1)
							type = "UNESCO World Heritage Site";
						else if (rs2.getString("OtherClass") != "")
							type = rs2.getString("OtherClass");

						s.setSite_category(type);
					}

				} catch (SQLException e) {
					System.out.println("Error en rs2 parte 2" + e);
				}

				s.setNetwork(rs.getString("network"));
				s.setChair(rs.getString("chair"));
				s.setCountry(rs.getString("country"));
				s.setName(rs.getString("name"));
				s.setShort_name(rs.getString("A"));
				s.setCordinator(rs.getString("sC"));
				s.setCordinator_email(rs.getString("sCe"));
				s.setWeb(rs.getString("sW"));
				s.setVariable(v1);

				sitios.add(s);
			}

		} catch (SQLException e) {
			System.out.println("SQLException findNetworkById " + e);
		}

		return sitios;
	}

	@Override
	public List<Variables> listarTodosLasVariables() {
		String sql = "SELECT * FROM Variables WHERE IdVariable < 1000 ORDER BY Orden, idVariable";
		List<Variables> variables = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				variables.add(new Variables(rs.getInt("IdVariable"), rs
						.getString("Variable"), rs.getString("Unidades")));
			}
		} catch (SQLException e) {
			System.out.println("Error en variables " + e);
		}

		return variables;
	}

	@Override
	public List<Object> datosParaGraficar(List<FormData> formData) {
		String campos = "SELECT DISTINCT d.IdRed as S1, s.IdSitio as F1, s.IdSubsitio as SU, s.Subsitio as SUN, d.red as N1, s.acronim A, s.Sitio as SS ";
		String from = " FROM Redes d, Sitios s " + " WHERE s.IdRed = d.IdRed "
				+ formData.get(formData.size() - 1).getSql()
				+ " order by d.IdRed, s.Sitio, s.IdSubsitio";

		List<Object> info = new ArrayList<>();
		List<Variables> x = new ArrayList<>();
		List<Variables> y = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(campos + from);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				String sql2 = "select d.red as N1, v.IdVariable, v.valor from Valores v, Redes d WHERE d.IdRed = v.IdRed and v.IdRed='"
						+ rs.getString("S1")
						+ "' and IdSitio="
						+ rs.getString("F1")
						+ " AND IdSubsitio="
						+ rs.getString("SU") + " and ";

				try (PreparedStatement st2 = con.prepareStatement(sql2
						+ " IdVariable=" + formData.get(0).getX()
						+ " and TipoValor='" + formData.get(0).getTipoX()
						+ "' Order by N1");
						ResultSet rs2 = st2.executeQuery()) {

					if (rs2.next()) {
						x.add(new Variables(rs2.getString("valor"), rs
								.getString("N1"), rs2.getInt("IdVariable")));
					}

				} catch (SQLException e) {
					System.out.println("Error en generateTable parte 1 " + e);
				}

				try (PreparedStatement st2 = con.prepareStatement(sql2
						+ " IdVariable=" + formData.get(1).getX()
						+ " and TipoValor='" + formData.get(1).getTipoX()
						+ "' Order by N1");
						ResultSet rs2 = st2.executeQuery()) {

					if (rs2.next()) {
						y.add(new Variables(rs2.getString("valor"), rs
								.getString("N1"), rs2.getInt("IdVariable")));

					}

				} catch (SQLException e) {
					System.out.println("Error en generateTable parte 1 " + e);
				}

			}

		} catch (SQLException e) {
			System.out.println("Error en generateTable" + e);
		}

		info.add(x);
		info.add(y);
		info.add(obtenerValoresAverage(campos + from));

		return info;
	}

	public List<SitiosRequested> obtenerValoresAverage(String sql) {
		List<SitiosRequested> s = new ArrayList<>();
		String valor = " - ";

		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				for (Variables v : listarTodosLasVariables()) {
					String sql2 = "select valor from Valores where IdRed='"
							+ rs.getString("S1") + "' and IdSitio="
							+ rs.getString("F1") + " AND IdSubsitio="
							+ rs.getString("SU") + " and IdVariable="
							+ v.getId() + " and TipoValor='Average'";

					try (PreparedStatement st2 = con.prepareStatement(sql2);
							ResultSet rs2 = st2.executeQuery()) {
						if (rs2.next())
							valor = Funciones.calcularValor(v.getId(),
									rs2.getString("valor"));
						else
							valor = " - ";

						s.add(new SitiosRequested(rs.getString("S1"), rs
								.getString("F1"), rs.getString("SU"), rs
								.getString("SUN"), rs.getString("N1"), rs
								.getString("A"), rs.getString("SS"), valor));

					} catch (SQLException e) {
						System.out
								.println("Error en findQueryNetwork Consulta 3"
										+ e);
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Error en findQueryNetwork Consulta 2 " + e);
		}

		return s;
	}

	@Override
	public List<SitiosRequested> buscarSitiosBiome(FormBiome formBiome) {
		String campos = "select distinct d.IdRed as S1, s.IdSitio as F1, s.IdSubsitio as SU, s.Subsitio as SUN, d.red as N1, s.acronim A, s.Sitio as SS, s.Class as SB, s.MAB, s.RAMSAR, s.UNESCOWHS";
		String from = " from Redes d, Sitios s, Valores v where s.IdRed = d.IdRed "
				+ formBiome.getSql(), where = "", order = " ORDER BY S1, F1, SU";
		boolean checked = false;

		if (formBiome.getCheck1() == true) {
			where += " s.MAB=1 ";
			checked = true;
		}
		if (formBiome.getCheck2() == true) {
			if (checked)
				where += " OR ";
			where += " s.RAMSAR=1 ";
			checked = true;
		}
		if (formBiome.getCheck3() == true) {
			if (checked)
				where += " OR ";
			where += " s.UNESCOWHS=1 ";
		}
		List<SitiosRequested> s = new ArrayList<>();
		System.out.println(campos + from + " AND( " + where + " )" + order);
		try (Connection con = DriverManager.getConnection(url + dbName,
				userName, password);
				PreparedStatement st = con.prepareStatement(campos + from
						+ " AND( " + where + " )" + order);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				s.add(new SitiosRequested(rs.getString("S1"), rs
						.getString("F1"), rs.getString("SU"), rs
						.getString("SUN"), rs.getString("N1"), rs
						.getString("A"), rs.getString("SS"),
						rs.getString("SB"), new String[] { rs.getString("MAB"),
								rs.getString("RAMSAR"),
								rs.getString("UNESCOWHS") }));
				System.out
						.println(rs.getString("MAB") + " "
								+ rs.getString("MAB") + " "
								+ rs.getString("UNESCOWHS"));
			}
			System.out.println(s);
		} catch (SQLException e) {
			System.out.println("Error en buscarSitiosBiome " + e);
		}

		return s;
	}

}
