package com.kpi.java.servlets;

import com.kpi.java.dtos.ProgramProductDTO;
import com.kpi.java.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(description = "Controller for getting full list of program products", urlPatterns = "/programs")
public class ProgramProductController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectId = req.getParameter("repositoryId");

        if (StringUtils.isNotBlank(projectId) && StringUtils.isNumeric(projectId)) {

            ProgramProductDTO programProductDTO = projectService.getProject(Long.valueOf(projectId));
            req.getSession().setAttribute("project", programProductDTO);
        } else {
            List<ProgramProductDTO> result = projectService.getAllExistedProject();
            req.getSession().setAttribute("projects", result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getReader().readLine();

        if (StringUtils.isNotBlank(name)) {
            projectService.addNewProjectUnderVersionControl(name);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputJson = req.getReader().readLine();

        if (StringUtils.isNotBlank(inputJson)) {
            projectService.deleteProjectFromVersionControl(inputJson);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputJson = req.getReader().readLine();

        if (StringUtils.isNotBlank(inputJson)) {
            projectService.updateProject(inputJson);
        }
    }
}
