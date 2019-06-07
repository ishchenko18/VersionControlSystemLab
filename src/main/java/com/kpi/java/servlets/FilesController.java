package com.kpi.java.servlets;

import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.services.FilesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
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
            log.info("Start creating new file.");

            ProgramProductDTO programProductDTO = filesService.createNewFileInRepository(jsonRequest);
            req.getSession().setAttribute("project", programProductDTO);
        } else {
            log.error("Json request is empty.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            log.info("Start deleting file with id: {}.", id);

            ProgramProductDTO programProductDTO = filesService.deleteFileFromVersionControl(Long.valueOf(id));
            req.getSession().setAttribute("project", programProductDTO);
        } else {
            log.error("File 'id' is empty or not numeric value.");
        }
    }
}
