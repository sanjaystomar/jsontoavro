package org.learning.converter;

import java.io.File;
import java.io.IOException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.learning.converter.employee.Employee;
import org.learning.converter.employee.Gender;

//@Slf4j
public class AppRunner {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AppRunner.class);

  public static void main(String[] args) {
    AppRunner appRunner = new AppRunner();

    Employee emp = new Employee(1, "sanjay", "tomar", "sanjay.tomar@xyz.com", Gender.MALE);
    Employee emp1 = new Employee(1, "somduti", "tomar", "sanjay.tomar@xyz.com", Gender.FEMALE);
    Employee emp2 = new Employee(1, "suraj", "tomar", "sanjay.tomar@xyz.com", Gender.MALE);

    //System.out.println(emp.getClassSchema().toString());

    DatumWriter<Employee> employeeDatumWriter = new SpecificDatumWriter<Employee>(Employee.class);
    DataFileWriter<Employee> dataFileWriter = new DataFileWriter<Employee>(employeeDatumWriter);
    try {
      dataFileWriter.create(emp.getClassSchema(), new File("employee.avro"));
      dataFileWriter.append(emp);
      dataFileWriter.append(emp1);
      dataFileWriter.append(emp2);
      dataFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    DatumReader<Employee> employeeDatumReader = new SpecificDatumReader<Employee>(Employee.class);
    DataFileReader<Employee> dataFileReader = null;
    try {
      dataFileReader = new DataFileReader<Employee>(new File("employee.avro"), employeeDatumReader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Employee employee = null;
    while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
      try {
        employee = dataFileReader.next(employee);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(employee);
    }


  }




















































































/*

    String json = "{\"id\": 1,\"first_name\": \"Kingsly\",\"last_name\": \"Nelsey\",\"email\": \"knelsey0@cyberchimps.com\",\"gender\": \"MALE\"}";
    try {
      Stream.of(appRunner.jsonToAvro(json, Employee.getClassSchema())).forEach(System.out::println);

      JSONObject testV=new JSONObject(appRunner.jsonToAvro(json, Employee.getClassSchema()));
      System.out.println(testV.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    Employee emp = new Employee(1, "sanjay", "tomar", "sanjay.tomar@xyz.com", Gender.MALE);
   */
/* try {
      appRunner.serealizeEmployeeJSON(emp);
    } catch (IOException e) {
      e.printStackTrace();
    }*//*

  }


  public byte[] jsonToAvro(String json, Schema schemaStr) throws IOException {
    InputStream input = null;
    DataFileWriter<GenericRecord> writer = null;
    Encoder encoder = null;
    ByteArrayOutputStream output = null;
    try {
//      Schema schema = new Schema.Parser().parse(schemaStr);
      DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schemaStr);
      input = new ByteArrayInputStream(json.getBytes());
      output = new ByteArrayOutputStream();
      DataInputStream din = new DataInputStream(input);
      writer = new DataFileWriter<GenericRecord>(new GenericDatumWriter<GenericRecord>());
      writer.create(schemaStr, output);
      Decoder decoder = DecoderFactory.get().jsonDecoder(schemaStr, din);
      GenericRecord datum;
      decoder.
      while (true) {
        try {
          datum = reader.read(null, decoder);
        } catch (EOFException eofe) {
          break;
        }
        writer.append(datum);
      }
      writer.flush();
      System.out.println(output);
      return output.toByteArray();
    } finally {
      try { input.close(); } catch (Exception e) { }
    }
  }


  public void serealizeEmployeeJSON(
      Employee employee) throws IOException {
    DatumWriter<Employee> dw = new GenericDatumWriter<>();
    dw.setSchema(Employee.getClassSchema());
    ByteArrayOutputStream out =  new ByteArrayOutputStream();
    JsonEncoder json = null;
      json = EncoderFactory.get().jsonEncoder(Employee.getClassSchema(), out);
      dw.write(employee, json);
      System.out.println("DUMP OF OBJECTS AS JSON. : \n");
     dw.write(employee, json);


 */
/*   DatumWriter<Employee> writer = new SpecificDatumWriter<>(Employee.class);
    byte[] data = new byte[0];
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    Encoder jsonEncoder = null;
    try {
      jsonEncoder = EncoderFactory.get().jsonEncoder(
          Employee.getClassSchema(), stream);
      writer.write(employee, jsonEncoder);
      jsonEncoder.flush();
      data = stream.toByteArray();
    } catch (IOException e) {
      log.error("Serialization error:" + e.getMessage());
    }
    return data;*//*

  }
*/

}

