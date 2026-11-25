package org.example;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoJuegoDAO implements DAO<VideoJuego> {
    private final DataSource ds;

    public VideoJuegoDAO(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<VideoJuego> save(VideoJuego v) {
        String sql = "INSERT INTO videojuegos (titulo, precio) VALUES (?, ?)";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getTitulo());
            ps.setDouble(2, v.getPrecio());
            int affected = ps.executeUpdate();
            if (affected == 0) return Optional.empty();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return Optional.of(new VideoJuego(id, v.getTitulo(), v.getPrecio()));
                }
            }
            return Optional.of(v);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar videojuego", e);
        }
    }

    @Override
    public Optional<VideoJuego> update(VideoJuego v) {
        String sql = "UPDATE videojuegos SET titulo = ?, precio = ? WHERE id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getTitulo());
            ps.setDouble(2, v.getPrecio());
            ps.setInt(3, v.getId());
            int affected = ps.executeUpdate();
            return affected > 0 ? Optional.of(v) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar videojuego id " + v.getId(), e);
        }
    }

    @Override
    public Optional<VideoJuego> delete(VideoJuego v) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, v.getId());
            int affected = ps.executeUpdate();
            return affected > 0 ? Optional.of(v) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar videojuego id " + v.getId(), e);
        }
    }

    @Override
    public List<VideoJuego> findAll() {
        String sql = "SELECT id, titulo, precio FROM videojuegos";
        List<VideoJuego> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los videojuegos", e);
        }
    }

    @Override
    public Optional<VideoJuego> findById(Integer id) {
        try(Connection conn = ds.getConnection()) {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM videojuegos WHERE id = ?");
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            if(rs.next()) {
                VideoJuego v = new VideoJuego();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    private VideoJuego map(ResultSet rs) throws SQLException {
        return new VideoJuego(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getDouble("precio")
        );
    }
}
