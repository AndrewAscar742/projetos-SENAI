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

import sp.senai.model.Boneca;

/**
 * Servlet implementation class ListarFuncionarioServlet
 */
@WebServlet("/FuncionarioAPI")
public class FuncionarioAPI extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Boneca> listaBoneca = new ArrayList<>();
		Gson gson = new Gson();
		Boneca boneca = new Boneca("Barbie Girl", "18 Anos", "Rihappy");

		listaBoneca.add(boneca);
		listaBoneca.add(boneca);
		listaBoneca.add(boneca);

		String JSON = gson.toJson(listaBoneca);
		response.getWriter().append("GET: " + JSON + "\n ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = "";

		BufferedReader br = req.getReader();

		String linha = "";
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Boneca boneca = gson.fromJson(json, Boneca.class);

		resp.getWriter().append("POST: " + json).append(req.getContextPath());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String json = "";
		String nome = req.getParameter("nome");

		BufferedReader br = req.getReader();

		String linha = "";
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Boneca boneca = gson.fromJson(json, Boneca.class);

		resp.getWriter().append("PUT: " + nome + json).append(req.getContextPath());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		resp.getWriter().append("DELETE: ").append(req.getContextPath());
	}

}
