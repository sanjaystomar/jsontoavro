package org.learning.converter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.learning.converter.employee.Gender;
import org.learning.converter.schemaBuilder.EmployeeAvroSchemaBuilder;

public class ConvertWithoutCodeGeneration {

  public static void main(String[] args) {
    Schema schema = EmployeeAvroSchemaBuilder.getAvroEmployeeSchema();
    GenericRecord emp1 = new GenericData.Record(schema);
    JSONParser parser = new JSONParser();
    try {
      Object obj = parser.parse(
          new FileReader("C:\\MINE\\IntelliJWS\\jsontoavro\\src\\main\\resources\\employee.json"));
      JSONObject jsonObject = (JSONObject) obj;
      emp1.put("id", jsonObject.get("id"));
      emp1.put("first_name", jsonObject.get("first_name"));
      emp1.put("last_name", jsonObject.get("last_name"));
      emp1.put("email", jsonObject.get("email"));
      emp1.put("gender", Gender.valueOf((String) jsonObject.get("gender")));

      // Serialize user1 and user2 to disk
      File file = new File("users.avro");
      DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
      DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
      dataFileWriter.create(schema, file);
      dataFileWriter.append(emp1);
      dataFileWriter.close();


    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    // Deserialize users from disk
    DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
    DataFileReader<GenericRecord> dataFileReader = null;
    try {
      dataFileReader = new DataFileReader<GenericRecord>(new File("users.avro"), datumReader);
      GenericRecord user = null;
      while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
        user = dataFileReader.next(user);
        System.out.println(user);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
