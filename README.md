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
    - nome: String
    - cpf: String
    + Pessoa(nome: String, cpf: String): void
    + getNome() String
    + setNome(nome: String)
    + getCpf() String
    + setCpf(cpf: String)
    + toString() String
    + equals(obj: Object) boolean
    + hashCode() int
  }

  class Funcionario {
    - salario: double
    - avaliacao: double
    - tempoAtendimento: String
    + Funcionario(salario: double, avaliacao: double, tempoAtendimento: String, nome: String, cpf: String): void
    + getSalario() double
    + setSalario(salario: double)
    + getAvaliacao() double
    + setAvaliacao(avaliacao: double)
    + getTempoAtendimento() String
    + setTempoAtendimento(tempoAtendimento: String)
    + toString() String
    + equals(obj: Object) boolean
    + hashCode() int
  }

  class Cliente {
    - veiculos: ArrayList~Veiculo~
    - frequencia: int
    - classificacao: String
    - idade: int
    - adimplente: boolean
    + Cliente(veiculos: Arraylist~Veiculo~, frequencia: int, classificacao: String, nome: String, cpf: String) void
    + getVeiculos(): ArrayList~Veiculo~
    + setVeiculos(veiculos: ArrayList~Veiculo~) void
    + getFrequencia() int
    + setFrequencia(frequencia: int) void
    + getClassificacao() String
    + setClassificacao(classificacao: String) void
    + getIdade() int
    + setIdade(idade: int) void
    + isAdimplente() boolean
    + setAdimplente(boolean adimplente) void
    + toString() String
    + equals(Object) boolean
    + hashCode() int
  }

  class Veiculo {
    - marcaModelo: String
    - ano: String
    - placa: String
    - tipo: String
    + Veiculo(marcaModelo: String, ano: String, placa, String, tipo: String) void
    + getMarcaModelo() String
    + setMarcaModelo(marcaModelo: String) void
    + getAno() String
    + setAno(ano: String) void
    + getPlaca() String
    + setPlaca(placa: String) void
    + getTipo() String
    + setTipo(tipo: String) void
    + toString() String
    + equals(Object) boolean
    + hashCode() int
  }

  class Vaga {
    - numero: int
    - tipo: String
    - preferencial: boolean
    - ocupada: boolean
    - veiculo: Veiculo
    + Vaga(numero: int, tipo: String, preferencial: boolean, ocuapada: boolean) void
    + getnumero() int
    + setNumero(numero: int) void
    + getTipo() String
    + setTipo(tipo: String) void
    + isPreferencial() boolean
    + setPreferencial(preferencial: boolean) void
    + isOcupada() boolean
    + setOcupada(ocupada: boolean) void
    + getVeiculo() Veiculo
    + setVeiculo(veiculo: Veiculo) void
    + toString() String
    + equals(Object) boolean
    + hashCode() int
  }

  class Ocorrencia {
    - tipo: String
    - veiculosEnvolvidos: ArrayList~Veiculo~
    - envolvidos: List~Cliente~ 
    + Ocorrencia(tipo: String, veiculosEnvolvidos: ArrayList~Veiculo~, envolvidos: ArrayList~Cliente~) void
    + getipo() String
    + settipo(tipo: String) void
    + getVeiculosEnvolvidos() ArrayList~Veiculo~
    + setVeiculosEnvolvidos(veiculosEnvolvidos~ ArrayList~Veiculo~) void
    + getEnvolvidos() ArrayList~Cliente~
    + setEnvolvidos(envolvidos: ArrayList~Cliente~) void
    + toString() String
    + equals(Object) boolean
    + hashCode() int
  }

  Pessoa <|-- Cliente
  Pessoa <|-- Funcionario
  
  Cliente "1" --> "N" Veiculo : possui
  Veiculo "0..1" --> "0..1" Vaga : ocupa
  Ocorrencia "N" --> "N" Cliente 
  Ocorrencia "N" --> "N" Veiculo
```
