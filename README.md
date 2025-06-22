# 🚗 Gerenciador de Carro

Projeto desenvolvido como parte da atividade avaliativa da Universidade Federal Rural de Pernambuco (UFRPE).  
O **Gerenciador de Estacionamento** é uma aplicação simples e intuitiva que ajuda no controle e monitoramento de veículos, voltado para usuários que desejam monitorar o estacionamento de seu estabelecimento.

---

## 📱 Sobre o Projeto

Tem como principal objetivo organizar e gerenciar um estacionamento, coletando dados dos usuários e das vagas

- Registrar informações básicas do veículo/Clente
- Calcular preço do alguel do estacionamento
- Adicionar e visualizar manutenções realizadas
- Controlar abastecimentos e gastos
- Gerar relatórios simples sobre o histórico do carro

---

## Diagrama de Classes

```mermaid
  classDiagram
    direction LR
  
    class Pessoa {
      - String nome
      - String cpf
    }
  
    class Funcionario {
      - double salario
      - double avaliacao
      - String tempoAtendimento
    }
  
    class Cliente {
      - List~Veiculo~ veiculos
      - int frequencia
      - String classificacao
      - int idade
      - boolean adimplente
    }
  
    class Veiculo {
      - String marca_modelo
      - String ano
      - String placa
      - String tipo
    }
  
    class Vaga {
      - int numero
      - String tipo
      - boolean pcd
      - boolean idoso
      - boolean ocupada
    }
  
    class Ocorrencia {
      - String tipo
      - List~Veiculo~ veiculos_envolvidos
      - List~Cliente~ envolvidos
      + String gerarRelatorio()
    }
  
    Pessoa <|-- Cliente
    Pessoa <|-- Funcionario
  
    Cliente "1" --> "N" Veiculo : possui
    Veiculo "0..1" --> "0..1" Vaga : ocupa
    Ocorrencia "N" --> "N" Cliente 
    Ocorrencia "N" --> "N" Veiculo
```
