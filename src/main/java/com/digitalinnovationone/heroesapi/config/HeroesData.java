package com.digitalinnovationone.heroesapi.config;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.REGION_DYNAMO;

public class HeroesData {

    static AmazonDynamoDB client  = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
            .build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static Table table = dynamoDB.getTable("Heroes_Table_Dio1");

    public static void main(String[] args) throws Exception {
        createItems();
    }

    private static void createItems(){
        Table table = dynamoDB.getTable("Heroes_Table_Dio1");
        try {
            Item hero1 = new Item()
                    /*.withPrimaryKey*/
                    .withString("id", "10")
                    .withString("name", "Mulher maravilha")
                    .withString("universe", "dc comics")
                    .withNumber("films", 1);
            table.putItem(hero1);
            Item hero2 = new Item()
                    .withString("id", "2")
                    .withString("name", "Capitan marvel")
                    .withString("universe", "marvel")
                    .withNumber("films", 2);
            table.putItem(hero2);
            Item hero3 = new Item()
                    .withString("id", "3")
                    .withString("name", "Thor")
                    .withString("universe", "marvel")
                    .withNumber("films", 3);
            table.putItem(hero3);
        }
        catch (Exception e){
            System.err.println("Create items failed.");
            System.err.println(e.getMessage());
        }

        //PutItemOutcome outcome1 = table.putItem(hero1);
        //PutItemOutcome outcome2 = table.putItem(hero2);
        //PutItemOutcome outcome3 = table.putItem(hero3);

    }

}
