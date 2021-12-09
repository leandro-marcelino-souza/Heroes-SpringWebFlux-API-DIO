package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.util.Arrays;

import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.REGION_DYNAMO;

//@Configuration
//@EnableDynamoDBRepositories
public class HeroesTable {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Heroes_Table_Dio1";

        try{
            System.out.println("Criando tabela, aguarde");
            Table table;
            table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)
                    ),
                    Arrays.asList(new AttributeDefinition("id" , ScalarAttributeType.S)
                    ),
                    new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
            System.out.println("Successo " + table.getDescription().getTableStatus());
        }
        catch (Exception e){
            System.err.println("Não foi possível criar a tabela");
            System.out.println(e.getMessage());
            /*Table table = dynamoDB.getTable(tableName);

                System.out.println("Issuing DeleteTable request for " + tableName);
                table.delete();*/
        }

    }

}
