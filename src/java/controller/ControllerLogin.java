
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.bean.Professores;
import model.dao.AreasDAO;
import model.dao.ProfessoresDAO;


@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin", "/login", "/cadastro", "/logar", "/cadastrar", "/inicio"})
@MultipartConfig
public class ControllerLogin extends HttpServlet {

    private ProfessoresDAO profDAO = new ProfessoresDAO();
    private AreasDAO areaDAO = new AreasDAO();

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
               
                
                request.getRequestDispatcher("WEB-INF/jsp/inicio.jsp").forward(request, response);
                
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
                
                
                prof.setNome(request.getParameter("inputNome"));
                prof.setSenha(request.getParameter("inputSenha"));
                prof.setCpf(request.getParameter("inputCpf"));
                prof.setId_area(Integer.parseInt(request.getParameter("selectArea")));
                prof.setAdmissao(Date.valueOf(request.getParameter("inputDate")));
                
                
    // Obtém a parte do arquivo de imagem da requisição
    Part filePart = request.getPart("inputImagem");
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

    // Corrige problemas com o navegador IE
    if (fileName != null && !fileName.isEmpty()) {
        // Se um arquivo de imagem foi enviado, salva-o no diretório de assets
        String basePath = getServletContext().getRealPath("/") + "assets"; // Caminho para a pasta assets
        File uploads = new File(basePath);
        if (!uploads.exists()) {
            uploads.mkdirs(); // Cria o diretório se não existir
        }
        File file = new File(uploads, fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace(); // Trate as exceções de forma adequada
        }

        // Configura o caminho relativo da imagem no banco de dados
        prof.setImagem("assets/" + fileName);
    } else {
        prof.setImagem(null);
    }

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
                
                if (profDAO.logar(cpf, senha)) {
                    
                    pag = "inicio";
                    
                } else {
                    
                    pag = "login";
                    
                } 
                
                response.sendRedirect("./" + pag);
                
                break;
                
            
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
