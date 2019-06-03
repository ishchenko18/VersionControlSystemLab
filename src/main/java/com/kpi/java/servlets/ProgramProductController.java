package com.kpi.java.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.java.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

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
        String result = StringUtils.EMPTY;

        try {
            result = projectService.getAllExistedProjects();
        } catch (JsonProcessingException e) {
            log.error("Error in processing object");
            resp.sendError(400, "Error in processing object");
        }

        resp.getWriter().write(result);
        req.setAttribute("name", "Gavno");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        projectService.addNewProjectUnderVersionControl(request);
    }
}
