package br.ufg.inf.es.fs.contpatri.model;

import br.ufg.inf.es.fs.contpatri.model.interfaces.IUsuario;

/**
 * Classe da pessoa
 *
 */
abstract class Pessoa implements IUsuario{

    /**
     * Identificador interno
     */
    private Long id;
    /**
     * Nome da pessoa
     */
    private String nome;
    /**
     * Matricula da pessoa
     */
    private String matricula;
    /**
     * Email da pessoa
     */
    private String email;
    /**
     * Senha da pessoa
     */
    private String senha;

    /**
     * Construtor de pessoa
     */
    public Pessoa() {
    }

    /**
     * Construtor da classe pessoa
     *
     * @param nome Nome da pessoa
     *
     * @param matricula Matrícula da pessoa
     *
     * @param email Email da pessoa
     */
    public Pessoa(String nome, String matricula, String email) {
        setNome(nome);
        setMatricula(matricula);
        setEmail(email);
    }

    /**
     * Define o email da pessoa
     *
     * @param email Email da pessoa
     */
    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException(
                    "Campo email deve ser preenchido");
        }
        this.email = email;
    }

    /**
     * Obtem o email da pessoa
     *
     * @return Email da pessoa
     */
    public String getEmail() {
        return email;
    }

    /**
     * Identificador interno para a aplicação
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador interno
     *
     * @param id Identificador interno
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Nome da pessoa
     *
     * @return nome da Pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa
     *
     * @param nome Nome da pessoa
     */
    public void setNome(String nome) {
        final int limiteNome = 60;
        if (nome == null || nome.trim().length() == 0
                || nome.length() > limiteNome) {
            throw new IllegalArgumentException(String.format(
                    "Valor inválido para o atributo nome: '%s'", nome));
        }
        this.nome = nome;
    }

    /**
     * Identificacao da pessoa
     *
     * @return Matricula da pessoa
     */
    public String getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Define a matricula da pessoa
     *
     * @param matricula Matricula da pessoa
     */
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().equals("")) {
            throw new IllegalArgumentException(
                    "Matricula nao pode ser nula ou vazia");
        }
        Long.parseLong(matricula);
        this.matricula = matricula;
    }
}
