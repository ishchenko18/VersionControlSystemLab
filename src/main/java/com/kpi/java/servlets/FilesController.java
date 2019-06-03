package com.kpi.java.servlets;

import com.kpi.java.entities.File;
import com.kpi.java.repositories.FileRepositoryImpl;
import com.kpi.java.repositories.Repository;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/files")
public class FilesController extends HttpServlet {

    private Repository<File> repository;

    @Override
    public void init() throws ServletException {
        repository = new FileRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (StringUtils.isNotBlank(id)) {
            Long longId = Long.valueOf(id);

            repository.delete(longId);
        }
    }
}
