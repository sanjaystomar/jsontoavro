package org.learning.converter.schemaBuilder;

    import org.apache.avro.Schema;
    import org.apache.avro.SchemaBuilder;

public class EmployeeAvroSchemaBuilder {

  public static Schema getAvroEmployeeSchema() {
    Schema avroEmployee = SchemaBuilder.record("Employee")
        .namespace("org.learning.converter.employee")
        .fields()
        .requiredInt("id")
        .requiredString("first_name")
        .requiredString("last_name")
        .requiredString("email")
        .name("gender").type().enumeration("Gender").symbols("MALE", "FEMALE").noDefault()
        .endRecord();
    return avroEmployee;
  }
}
