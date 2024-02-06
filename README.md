<p align="center">
  <a href="" rel="noopener">
 <img src="Hackathon.png" alt="Project logo"></a>
</p>
<h3 align="center">Hackathon ASAP</h3>

<div align="center">

[![Hackathon](https://img.shields.io/badge/hackathon-name-orange.svg)](http://hackathon.url.com)
[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)

</div>

---
# Descrição do Projeto: Funcionamento da Aplicação

## Ingestão e Processamento de Dados
<p> A aplicação principal, chamada "ingestao," foi desenvolvida em Java com o framework Spring. Seu propósito é processar arquivos CSV localizados nas pastas "conciliacoes" e "transacoes." Garanta que essas pastas estejam presentes no repositório; caso contrário, crie-as com exatamente esses nomes.

Após identificar e processar os arquivos CSV, a aplicação utiliza uma imagem Docker-Compose para enviar os dados para o RabbitMQ, formatados em JSON. Esse processo é gerenciado pelas lógicas implementadas nos arquivos `FileObservers.java` e `RabbitMQSender.java`. Uma vez enviados, os arquivos processados são automaticamente movidos para as pastas "transacoes_processado" e "conciliacoes_processado. Isso proporciona um histórico completo das operações realizadas, contribuindo para a integridade e rastreabilidade dos dados.

Essa abordagem permite que o sistema evolua conforme a estrutura dos dados evolui, garantindo flexibilidade e robustez na gestão das informações dos clientes. Certifique-se de que a estrutura do arquivo JSON esteja alinhada com os requisitos do banco de dados para uma persistência eficiente e precisa dos dados."

A monitorização contínua de novos arquivos nas pastas "transacoes" e "conciliacoes" é realizada pela aplicação `FileObservers.java`, assegurando que qualquer novo arquivo CSV seja prontamente identificado e processado.
    <br> 
</p>

## Ingestão para o Banco de Dados
<p> Além da ingestão e processamento de dados, o projeto inclui a aplicação "ingestoodb." Esta aplicação tem a função de receber as mensagens em formato JSON provenientes do RabbitMQ e persisti-las no banco de dados. Uma camada de mensageria está sempre atenta às mensagens do RabbitMQ, garantindo a contínua persistência dos dados no banco.
    <br> 
</p>

## Banco de Dados 
<p> O banco de dados, criado internamente pela aplicação "ingestoodb," é dinâmico e adaptado à estrutura do arquivo JSON recebido.
    <br> 
</p>

## 📝 Conteúdo

- [Problema Proposto](#problema_proposto)
- [Solução](#solucao)
- [Dependencias](#dependencias)
- [Ponto de melhoria](#ponto_de_melhoria)
- [Prerequisitos](#prerequisitos)
- [Ferramentas da Aplicação](#ferramentas)
- [Autores](#autores)
- [Agradecimentos](#agradecimentos)

## 🧐 Problema Proposto <a name = "problema_proposto"></a>

O candidato deve desenvolver um programa para integrar camadas de processamento financeiro. Inicialmente, o programa recebe um arquivo CSV com dados de transações e deve parseá-lo, criando registros em tabelas relacionais. Posteriormente, a empresa fornecedora do arquivo enviará outro CSV com informações de conciliação, incluindo o status de cada transação. O desafio inclui a persistência desses dados no sistema já em uso.


## 💡 Solução <a name = "solucao"></a>

Solução proposta: O candidato precisa desenvolver dois programas. O primeiro lê o arquivo de transações, cria registros nas tabelas PERSON, TRANSACTION e INSTALLMENT, conforme as regras estabelecidas. O segundo programa processa o arquivo de conciliação, atualiza o status na tabela TRANSACTION e ajusta o valor das transações de acordo com o status recebido. Além disso, a evolução do sistema inclui a necessidade de tornar a aplicação 24/7, observando diretórios para iniciar o processamento sempre que um arquivo for transferido. Por fim, deve-se criar containers Docker para todas as aplicações.


## ⛓️ Dependencias <a name = "dependencias"></a>

  Ambiente de Execução: A aplicação depende de um ambiente de execução adequado, como um servidor ou máquina que suporte a execução contínua.

  Bibliotecas e Frameworks: O desenvolvimento do programa pode exigir o uso de bibliotecas ou frameworks específicos para manipulação de CSV, geração de UUID, persistência em banco de dados relacional, e observação de diretórios.

  Banco de Dados: É necessário um banco de dados relacional compatível com as operações descritas no projeto, como criação de tabelas, relacionamentos e atualizações.

  Conectividade: Para a atualização do sistema com o segundo arquivo de conciliação, é preciso garantir a disponibilidade e conectividade com a fonte desse arquivo.

## 🚀 Ponto de melhoria <a name = "ponto_de_melhoria"></a>

Próximo escopo a ser incrementado seria a incorporaração de um sistema abrangente de logs permitindo o rastreamento detalhado de atividades, facilitando a detecção e resolução de problemas. Além disso, a inclusão de ferramentas de monitoramento para avaliar o desempenho da aplicação, identificar gargalos e antecipar potenciais falhas.

## 🏁 Prerequisitos <a name = "prerequisitos"></a>


Para o funcionamento deste projeto em sua máquina local é necessário a instalação dos programas abaixo
```
RabbitMQ
Erlang/OTP for RabbitMQ
SpringToolSuite
JDK Development Kit
MySQL
```
Ao executar o programa é necessário alterar o caminho dentro do código de execução para o caminho onde se encontram os arquivos CSV a serem lidos

```
C:\Users\username\Documents\workspace-spring-tool-suite-4-4.20.1.RELEASE\hackathon-asapcard
```

## ⛏️ Ferramentas da Aplicação <a name = "ferramentas"></a>

- [MySQL](https://www.mysql.com/) - Database
- [RabbitMQ](https://www.rabbitmq.com/) - Canal de transmissão de Mensagens
- [SpringTools](https://spring.io/tools) - Web Framework
- [Java](https://www.oracle.com/java/technologies/downloads/) - Programming Language

## ✍️ Autores <a name = "autores"></a>

- [@verofreitt](https://github.com/verofreitt)

- [@guilhermerubrae](https://github.com/guilhermerubrae)

- [@lucianajrocha](https://github.com/lucianajrocha)

- [@letssrockit](https://github.com/letssrockit)

- [@matheuslib](https://github.com/MatheusLib)

Veja também a a lista de [contribuidores](https://github.com/verofreitt/hackathon-asapcard/graphs/contributors)
que participaram neste projeto.

## 🎉 Agradecimentos <a name = "agradecimentos"></a>

- Agradecimentos a equipe ASAP pela promoção do evento 1º HACKATON ASAPCARD
