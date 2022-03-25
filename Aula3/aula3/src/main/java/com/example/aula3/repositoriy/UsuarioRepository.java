package com.example.aula3.repositoriy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.aula3.entity.Usuario;

import org.hibernate.sql.Delete;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private static String INSERT =
        "insert into usuario (nome,email,senha) values (?,?,?)";
        private static String DELETE =
        "DELETE FROM USUARIO WHERE ID = ?";
        private static String UPDATE =
        "UPDATE usuario set nome=?,email=?,senha=? where id = ? ";
        private static String AUTENTICAR = 
        "Select * from usuario where email =? and senha =?";

    private static String SELECT_ALL = "select * from usuario";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario inserir(Usuario usuario){
        jdbcTemplate.update(INSERT,new Object[]
            {usuario.getNome(),usuario.getEmail(),usuario.getSenha()});
        return usuario;
    }

    public List<Usuario> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Usuario>(){

            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"));
            }
            
        });      
    } 
    //ver se está funcionando
    public void deleteUsuario(int idUsuario){
        jdbcTemplate.update(DELETE, idUsuario );           

    }

    public Usuario update(Usuario usuario){

        jdbcTemplate.update(UPDATE, usuario);

        return usuario;

    }

    
    public boolean autenticaUsuario(Usuario usuario){
        if( jdbcTemplate.update(AUTENTICAR) != null ){
            return true;
        }
        else{
            return false;
        }
        
    }


}
