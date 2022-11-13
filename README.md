# hanifm-anyx-test
test project for anyMind group

Project Spec:
- Lang: Java 8
- Framework: Spring Boot 2.7.5
- Build Tool: Maven
- API: GraphQL

## How to run:
1. with your favourite IDE, run this java class:

   [HanifmAnyxTestApplication.java](src/main/java/hanifm/anyx/hanifm/anyx/test/HanifmAnyxTestApplication.java)

2. To try the application, choose one 

    a. visit this url: [localhost](http://localhost:8080/graphiql)
   
    b. use **Postman**, import this [Postman_Collection](anymind_e-comm.postman_collection.json) into Postman. Download [Here](https://www.postman.com/downloads/) if you don't have postman

3. Query for calculate payment:

    - Query:
    ```graphql
    mutation CalculatePayment($calculationRq: CalculationRq!) {
      calculatePayment(paymentRq: $calculationRq) {
        data {
          final_price
          points
        }
        error
      }
    }
    ```
    - Variable:
    ```json
    {
      "calculationRq": {
        "price": "100",
        "price_modifier": 0.95,
        "payment_method": "CASH",
        "datetime": "2022-09-01T00:00:00Z"
      }
    }    
    ```
4. Query for get Sales Log:

    - Query:
    ```graphql
    query GetSalesLog($salesLogRq: SalesLogRq!) {
        GetSalesLog(SalesLogRq: $salesLogRq) {
            data {
                datetime
                sales
                points
            }
            error
        }
    } 
    ```
    - Variable:
    ```json
    {
      "salesLogRq": {
        "startDateTime": "2022-09-01T00:00:00Z",
        "endDateTime": "2022-09-01T00:00:00Z"
      }
    }
    ```