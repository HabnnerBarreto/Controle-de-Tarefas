package controledetarefas;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Componentes {
    Scanner entrada = new Scanner(System.in);
    int opc;
    
    // Listas para armazenar clientes, tarefas e especialidades
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Tarefa> tarefas = new ArrayList<>();
    ArrayList<Especialidades> especialidades = new ArrayList<>();
    
    // Métodos principais do programa
    public void Executar(){
        while(true){
            Menu();
            escolherOpc();
            
            switch (opc){
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    AddEspecialidade();  
                    break;
                case 3:
                    CriarTarefa();
                    break;
                
                case 4:
                    PesqCliente();
                    break;
                case 5:
                    PesqEspecialidade();  
                    break;
                case 6:
                    PesqTarefa();
                    break;
                case 7:
                    sairPrograma();
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    
    // Exibe o menu de opções
    public void Menu(){
        System.out.println("***** MENU *****");
        System.out.println("[1] Cadastrar Clientes");
        System.out.println("[2] Cadastrar Especialidade");
        System.out.println("[3] Cadastrar Tarefa");
        System.out.println("[4] Pesquisar Cliente");
        System.out.println("[5] Pesquisar Tarefa");
        System.out.println("[6] Pesquisar Especialidade");
        System.out.println("[7] Sair");
    }
    
    // Lê a opção do menu escolhida pelo usuário
    public void escolherOpc(){
        System.out.print("Escolha uma opção: ");
        opc = entrada.nextInt();
        entrada.nextLine();  // Limpar o buffer de entrada
    }
    
    // Cadastrar um novo cliente
    public void cadastrarCliente(){
        System.out.println("Digite o CPF do Cliente: ");
        String cpf = entrada.nextLine();
        System.out.println("Digite o nome do Cliente: ");
        String nome = entrada.nextLine();
        
        // Armazenar o cliente
        Cliente cliente = new Cliente(cpf, nome);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Cadastrar uma nova especialidade e vincular a um cliente
    public void AddEspecialidade(){
        System.out.println("Digite o CPF do Cliente para vincular a especialidade: ");
        String cpfCliente = entrada.nextLine();
        
        // Verificar se o cliente existe
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.cpf.equals(cpfCliente)) {
                clienteEncontrado = cliente;
                break;
            }
        }
        
        if (clienteEncontrado == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        // Adicionar especialidade ao cliente
        System.out.println("Digite a Especialidade do Cliente: ");
        String especialidade = entrada.nextLine();
        System.out.println("Principais temas Trabalhados: ");
        String descricao_espec = entrada.nextLine();
        
        // Armazenar a especialidade
        Especialidades novaEspecialidade = new Especialidades(especialidade, descricao_espec);
        clienteEncontrado.addEspecialidade(novaEspecialidade);  // Vincula a especialidade ao cliente
        System.out.println("Especialidade cadastrada com sucesso!");
    }

    // Criar uma nova tarefa e vincular ao cliente
    public void CriarTarefa(){
        System.out.println("Digite o CPF do Cliente para vincular a tarefa: ");
        String cpfCliente = entrada.nextLine();
        
        // Verificar se o cliente existe
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.cpf.equals(cpfCliente)) {
                clienteEncontrado = cliente;
                break;
            }
        }
        
        if (clienteEncontrado == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        // Adicionar tarefa ao cliente
        System.out.println("Tema da Tarefa: ");
        String tema = entrada.nextLine();
        System.out.println("Descrição da Tarefa: ");
        String descricao = entrada.nextLine();
        
        // Armazenar a tarefa
        Tarefa novaTarefa = new Tarefa(tema, descricao);
        clienteEncontrado.addTarefa(novaTarefa);  // Vincula a tarefa ao cliente
        System.out.println("Tarefa cadastrada com sucesso!");
    }
    
    // Pesquisar cliente pelo CPF
    public void PesqCliente(){
        System.out.println("Digite o CPF do cliente para pesquisa: ");
        String cpfPesquisa = entrada.nextLine();
        
        // Pesquisar cliente
        boolean encontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.cpf.equals(cpfPesquisa)) {
                System.out.println("Cliente encontrado: " + cliente);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Pesquisar tarefa pelo tema
    public void PesqTarefa(){
        System.out.println("Digite o tema da tarefa para pesquisa: ");
        String temaPesquisa = entrada.nextLine();
        
        // Pesquisar tarefa
        boolean encontrado = false;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.tema.equalsIgnoreCase(temaPesquisa)) {
                System.out.println("Tarefa encontrada: " + tarefa);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Tarefa não encontrada.");
        }
    }
    
    // Pesquisar especialidade pelo nome
    public void PesqEspecialidade(){
        System.out.println("Digite a Especialidade para pesquisa: ");
        String espec_pesquisa = entrada.nextLine();
        
        // Pesquisar especialidade
        boolean encontrado = false;
        for (Especialidades especialidade : especialidades) {
            if (especialidade.especialidade.equalsIgnoreCase(espec_pesquisa)) {
                System.out.println("Especialidade encontrada: " + especialidade);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Especialidade não encontrada.");
        }
    }

    // Método para sair do programa
    public void sairPrograma(){
        System.out.println("Encerrando programa...");
        System.exit(0);
    }
}

class Cliente {
    String cpf;
    String nome;
    ArrayList<Especialidades> especialidades = new ArrayList<>();  // Lista de especialidades do cliente
    ArrayList<Tarefa> tarefas = new ArrayList<>();  // Lista de tarefas do cliente

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Método para adicionar uma especialidade ao cliente
    public void addEspecialidade(Especialidades especialidade) {
        especialidades.add(especialidade);
    }

    // Método para adicionar uma tarefa ao cliente
    public void addTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Especialidades: " + especialidades + ", Tarefas: " + tarefas;
    }
}

class Tarefa {
    String tema;
    String descricao;
    LocalDate dataCriacao;
    LocalDate dataEntrega;

    public Tarefa(String tema, String descricao) {
        this.tema = tema;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.dataEntrega = dataCriacao.plusDays(7);
    }

    @Override
    public String toString() {
        return "Tarefa: " + tema + ", Descrição: " + descricao + ", Data de Criação: " + dataCriacao + ", Data de Entrega: " + dataEntrega;
    }
}

class Especialidades {
    String especialidade;
    String descricao_espec;

    public Especialidades(String especialidade, String descricao_espec) {
        this.especialidade = especialidade;
        this.descricao_espec = descricao_espec;
    }

    @Override
    public String toString() {
        return "Especialidade: " + especialidade + ", Descrição: " + descricao_espec;
    }
}
