
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.bean.Professores;
import model.dao.AreasDAO;
import model.dao.ProfessoresDAO;


@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin", "/login", "/cadastro", "/logar", "/cadastrar", "/inicio", "/logout"})
@MultipartConfig
public class ControllerLogin extends HttpServlet {

    private ProfessoresDAO profDAO = new ProfessoresDAO();
    private AreasDAO areaDAO = new AreasDAO();
    private int id_usuario = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String paginaAtual = request.getServletPath();
        
        switch(paginaAtual) {
            
            case "/login":
                
                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
                
                break;
                
            case "/cadastro":
                
                request.setAttribute("areas", areaDAO.listar());
                
                request.getRequestDispatcher("WEB-INF/jsp/cadastro.jsp").forward(request, response);
                
                break;
                
            case "/inicio":
                
                Cookie[] cookies = request.getCookies();
                
                for(Cookie c: cookies) {
                    
                    if (c.getName().equals("id_usuario")) {
                     
                        id_usuario = Integer.parseInt(c.getValue());
                        
                    }
                    
                }
                
                if (id_usuario > 0) {
                    
                    Cookie[] cookies2 = request.getCookies();
                
                for(Cookie c: cookies2){
                  
                    request.setAttribute(c.getName(), c.getValue());
                    
                }
                
                request.getRequestDispatcher("WEB-INF/jsp/inicio.jsp").forward(request, response);
                    
                } else {
                    
                    request.setAttribute("very", true);
                    request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
                    
                }
                
                break;
            
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String paginaAtual = request.getServletPath();
        
        switch(paginaAtual) {
            
            case "/cadastrar":
                
                Professores prof = new Professores();
                
                Part filePart = request.getPart("inputImagem");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                
                if (fileName != null && !fileName.isEmpty()) {
                    
                    String basePath = getServletContext().getRealPath("/") + "assets";
                    
                    File uploads = new File(basePath);
                    
                    if (!uploads.exists()) {
                        
                        uploads.mkdir();
                        
                    }
                    
                    File file = new File(uploads, fileName);
                    
                    try(InputStream input = filePart.getInputStream()){
                        
                        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    prof.setImagem("assets/" + fileName);
                    
                }
                
                prof.setNome(request.getParameter("inputNome"));
                prof.setSenha(request.getParameter("inputSenha"));
                prof.setCpf(request.getParameter("inputCpf"));
                prof.setId_area(Integer.parseInt(request.getParameter("selectArea")));
                prof.setAdmissao(Date.valueOf(request.getParameter("inputDate")));
                
                if (prof.getNome().isEmpty() || prof.getSenha().isEmpty() || prof.getCpf().isEmpty() || prof.getAdmissao() == null || prof.getId_area() == 0) {
                    
                    response.sendRedirect("./cadastro");
                    
                } else {
                    
                    profDAO.cadastrar(prof);
                
                    request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
                    
                }

                break;
                
            case "/logar":
                
                String cpf = request.getParameter("inputCpf");
                String senha = request.getParameter("inputSenha");
                String pag = null;
                
                Professores profe = new Professores();
                
                profe = profDAO.logar(cpf, senha);
                
                if (profe.getId_professor() == 0) {
                    
                    pag = "login";
                    
                } else {
                    
                    id_usuario = profe.getId_professor();
                    
                    Cookie cookie = new Cookie("id_professor", Integer.toString(profe.getId_professor()));
                    Cookie cookie2 = new Cookie("nome", profe.getNome());
                    Cookie cookie3 = new Cookie("imagem", profe.getImagem());
                    
                    response.addCookie(cookie);
                    response.addCookie(cookie2);
                    response.addCookie(cookie3);
                    pag = "inicio";
                    
                }
                
                response.sendRedirect("./" + pag);
                
                break;
                
            case "/logout":
                
                Cookie[] cookies = request.getCookies();
                
                id_usuario = 0;
                
                for(Cookie c: cookies){
                  
                    c.setMaxAge(0);
                    c.setValue("");
                    response.addCookie(c);
                    
                }
                
                response.sendRedirect("./login");
                
                break;
                
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
