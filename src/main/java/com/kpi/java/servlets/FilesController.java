package com.kpi.java.servlets;

import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.services.FilesService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/files")
public class FilesController extends HttpServlet {

    private FilesService filesService;

    @Override
    public void init() throws ServletException {
        filesService = new FilesService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonRequest = req.getReader().readLine();

        if (StringUtils.isNotBlank(jsonRequest)) {
            ProgramProductDTO programProductDTO = filesService.createNewFileInRepository(jsonRequest);
            req.getSession().setAttribute("project", programProductDTO);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            filesService.deleteFileFromVersionControl(Long.valueOf(id));
        }
    }
}
