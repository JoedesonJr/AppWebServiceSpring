package br.ufsm.csi.dao;

import br.ufsm.csi.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDao {

    private Connection conn = (Connection) PostgreSQLFactory.getConexao();
    private PreparedStatement stmt;
    private String query;

    public Usuario autenticarUsuario(Usuario usuario) {

        this.query = " SELECT id, nome, tecnico, endereco FROM usuario WHERE login = ? AND senha = ?; ";

        try{
            stmt = conn.prepareStatement(this.query);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Usuario usuarioAutenticado = new Usuario();
                    usuarioAutenticado.setNome(rs.getString("nome"));
                    usuarioAutenticado.setId(rs.getLong("id"));
                    usuarioAutenticado.setTecnico(rs.getString("tecnico"));
                    usuarioAutenticado.setEndereco(rs.getString("endereco"));
                return usuarioAutenticado;
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Usuario cadastrarUsuario(Usuario usuario) throws Exception {

        query = " INSERT INTO usuario (id, nome, login, senha, telefone, tecnico, endereco) "
            + " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";

        stmt = conn.prepareStatement(query);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTelefone());
        stmt.setString(5, usuario.getTecnico());
        stmt.setString(6, usuario.getEndereco());

        try{
            stmt.execute();
            stmt.close();
            conn.close();

            return usuario;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        this.query = " SELECT * FROM usuario WHERE tecnico = ?; ";

        try{
            stmt = conn.prepareStatement(this.query);
            stmt.setString(1, "sim");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setEndereco(rs.getString("endereco"));
                usuarios.add(usuario);
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario alterarUsuario(Usuario usuario) throws Exception {

        query = " UPDATE usuario SET nome=?, login=?, senha=?, telefone=?, endereco=? WHERE id=?;";

        stmt = conn.prepareStatement(query);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTelefone());
        stmt.setString(5, usuario.getEndereco());
        stmt.setLong(6, usuario.getId());

        try{
            stmt.execute();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();

            return null;
        }

        return usuario;
    }

    public Usuario buscarUsuario(Usuario u) {

        this.query = " SELECT * FROM usuario WHERE id = ?; ";

        try{
            stmt = conn.prepareStatement(this.query);
            stmt.setLong(1, u.getId());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEndereco(rs.getString("endereco"));
                return usuario;
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
