package sp.senai.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sp.senai.dao.BonecaDAO;
import sp.senai.model.Boneca;

/**
 * Servlet implementation class ListarFuncionarioServlet
 */
@WebServlet("/FuncionarioAPI")
public class FuncionarioAPI extends HttpServlet {
	List<Boneca> listaBoneca = new ArrayList<>();
	BonecaDAO bancoDeDados = new BonecaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		listaBoneca = bancoDeDados.listar();

		String JSON = gson.toJson(listaBoneca);
		response.getWriter().append("GET: " + JSON + "\n ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = "";

		BufferedReader br = req.getReader(); // ele vai ler o que estiver no HTTP Entity Body

		String linha = "";
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Boneca boneca = gson.fromJson(json, Boneca.class);
		bancoDeDados.adicionar(boneca);

		resp.getWriter().append("POST: " + json).append(req.getContextPath());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String json = "";
		int id = Integer.parseInt(req.getParameter("idBoneca"));

		BufferedReader br = req.getReader();

		String linha = "";
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Boneca bonecaJSON = gson.fromJson(json, Boneca.class);
	
		if ((bancoDeDados.buscarBoneca(id)) != null && bancoDeDados.alterar(bonecaJSON, id)) 
			System.out.println("Efetuado com sucesso!");
		else
			System.err.println("Não foi alterado!");

			resp.getWriter().append("PUT: " + bonecaJSON.getIdBoneca() + json).append(req.getContextPath());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idBoneca");
		String text = "";
		
		if (bancoDeDados.excluir(Integer.parseInt(id)))
			text = "Excluido com sucesso";
		else
			text = "Não houve sucesso";

		resp.getWriter().append("DELETE: " + text).append(req.getContextPath());
	}

}
