# Sockets
Atividade prática da matéria Sistemas Distribuídos, onde foram criadas 3 aplicações aplicando os conhecimentos de protocolos UDP e 
TCP/IP e servidores RMI.

## Alunos
- Mayla Jamile Carrera da Silva
- Ranniher da Silva Rosa

## Ferramentas

- JDK 11
- Eclipse IDE

## Instruções:

<strong>Atividade 1: Se trata de uma calculadora usando o protocolo UDP.</strong>
 - Executar a classe ServidorUDP responsavél por criar o socket de conexão.
 - Executar a classe ClienteUDP que irá iniciar a tela da calculadora.
 - A operação deve ser passado no seguinte formato: num [sinal] num; exemplo: (1 + 2).
 - Clicar no botão calcular para receber a resposta.
 
<strong>Atividade 2: Se trata de um classificados de veículos usando um servidor RMI.</strong>
 - Executar a classe Servidor para iniciar o servidor RMI.
 - Executar a classe Cliente para se comunicar com o servidor.
 - Utilizar o método adicionarVeiculo() para adiciona-lo na lista de classificados.
 - Utilizar o método pesquisar() para buscar os carros listados a partir da marca do carro.

<strong>Atividade 3: Se trata de um chat usando protocolo TCP/IP.</strong>
 - Executar a classe ServidorTCPIP responsavél por criar o socket de conexão.
 - Executar a classe ClienteTCPIP responsavel por abrir a tela de chat do usuário.
 - Digitar o nome de usuário e mensagem.
 - Clicar no botão enviar para que seja enviada a mensagem para os outros clientes conectados ao chat.
 - Digitar "#QUIT" para se desconectar do chat.
 - Digitar "#USERS" para listar usuários conectados ao chat.
 

