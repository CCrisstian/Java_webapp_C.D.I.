package org.CCristian.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.CCristian.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.CCristian.apiservlet.webapp.headers.models.Categoria;
import org.CCristian.apiservlet.webapp.headers.models.Producto;
import org.CCristian.apiservlet.webapp.headers.repositories.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@ProductoServicePrincipal
public class ProductosServiceJdbcImpl implements ProductoService{

    @Inject
    private Repository<Producto> repositoryJdbc;

    @Inject
    private Repository<Categoria> repositoryCategoriaJdbc;

    @Override
    public List<Producto> listar(){
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}