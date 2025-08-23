## INSTRUÇÕES / INSTRUCTIONS
(PT)
O método de entrada da aplicação (main) se encontra na classe Main. Para executar o simulador, comentar as linhas 22-24 e descomentar as linhas 10-14.

Para executar o benchmark, comentar as linhas 10-14 e descomentar as linhas 22-24.

Por padrão o projeto vem configurado para executar o simulador.

O que define se ocorrerá ou não o deadlock é o método run implementado nas classes ProcessA e ProcessB.

Para executar o deadlock, basta descomentar as linhas que fazem a solicitação do recurso utilizando o algoritmo do avestruz e comentar as linhas referentes à execução do algoritmo do banqueiro: métodos getResource, useResources e releaseResources.

Para seguir o fluxo completo sem deadlock, basta comentar as requisições de recursos utilizando o algoritmo do avestruz e descomentar as instruções referentes ao algoritmo do banqueiro.

Convém ressaltar que as alterações devem ser realizadas tanto na classe ProcessA quanto na ProcessB.

Por padrão, o projeto vem configurado para executar o fluxo completo com o algoritmo do banqueiro.

(EN)
The application's entry method (main) is located in the Main class. To run the simulator, comment out lines 22-24 and uncomment lines 10-14.

To run the benchmark, comment out lines 10-14 and uncomment lines 22-24.

By default, the project is configured to run the simulator.

What determines whether a deadlock occurs is the run method implemented in the ProcessA and ProcessB classes.

To execute the deadlock, simply uncomment the lines that request resources using the ostrich algorithm and comment out the lines related to executing the banker's algorithm: the getResource, useResources, and releaseResources methods.

To follow the complete flow without a deadlock, simply comment out the resource requests using the ostrich algorithm and uncomment the instructions related to the banker's algorithm.

It should be noted that the changes must be made to both the ProcessA and ProcessB classes.

By default, the project is configured to run the full flow with the banker's algorithm.

## EQUIPE / TEAM
- Fernanda da Silva Santos
- Jackson Santana Carvalho Junior
- João Santos Rocha
- José Henrique Souza Santana
- Lais Santos de Souza
- Pedro Thalles Lima Ricarte