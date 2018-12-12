package dados;

import connection.ConnectionFactory;
import negocios.entidades.Endereco;
import negocios.entidades.Funcionario;

import java.sql.*;

public class DbFuncionario {

    public void adicionarFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO funcionario (nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, senha, gerente) VALUES (?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?) ");

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            stmt.setDate(3, (Date) funcionario.getDataDeNascimento());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getEndereco().getCep());
            stmt.setString(7, funcionario.getEndereco().getNumero());
            stmt.setString(8, funcionario.getEndereco().getRua());
            stmt.setString(9, funcionario.getEndereco().getBairro());
            stmt.setString(10, funcionario.getEndereco().getCidade());
            stmt.setString(11, funcionario.getSenha());
            stmt.setBoolean(12, funcionario.isGerente());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }


    public Funcionario buscarFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Funcionario funcionarioBanco= null;
        Endereco endereco = null;
        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, cidade, senha, gerente " +
                    "FROM cliente WHERE cpf ="+funcionario.getCpf());
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));

                funcionarioBanco.setNome(rs.getString("nome"));
                funcionarioBanco.setSexo(rs.getString("sexo"));
                funcionarioBanco.setDataDeNascimento(rs.getDate("dataDeNacimento"));
                funcionarioBanco.setEndereco(endereco);
                funcionarioBanco.setSenha(rs.getString("senha"));
                funcionarioBanco.setGerente(rs.getBoolean("gerente"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return funcionarioBanco;
    }

    //Metodo de sobrecarga
    public Funcionario buscarCliente(String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Funcionario funcionarioBanco= null;
        Endereco endereco = null;
        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, cidade, senha, gerente " +
                    "FROM cliente WHERE cpf ="+cpf);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));

                funcionarioBanco.setNome(rs.getString("nome"));
                funcionarioBanco.setSexo(rs.getString("sexo"));
                funcionarioBanco.setDataDeNascimento(rs.getDate("dataDeNacimento"));
                funcionarioBanco.setEndereco(endereco);
                funcionarioBanco.setSenha(rs.getString("senha"));
                funcionarioBanco.setGerente(rs.getBoolean("gerente"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return funcionarioBanco;
    }

    public boolean esvaziou(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean vazio = false;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()) {
                vazio = true;
            }else{
                vazio = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return vazio;
    }

    public void atualizarCliente(String cpfFuncionario, Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE funcionario SET nome = ?, sexo = ?, dataDeNascimento = ?," +
                    " cpf = ?, telefone = ?, cep = ?, numero = ?, rua = ?, bairro = ?, cidade = ?, senha = ?," +
                    " gerente = ? WHERE cpf =" + cpfFuncionario);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            stmt.setDate(3, (Date) funcionario.getDataDeNascimento());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getEndereco().getCep());
            stmt.setString(7, funcionario.getEndereco().getNumero());
            stmt.setString(8, funcionario.getEndereco().getRua());
            stmt.setString(9, funcionario.getEndereco().getBairro());
            stmt.setString(10, funcionario.getEndereco().getCidade());
            stmt.setString(11, funcionario.getSenha());
            stmt.setBoolean(12, funcionario.isGerente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void removerFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  funcionario WHERE cpf =" + funcionario.getCpf());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }
}
