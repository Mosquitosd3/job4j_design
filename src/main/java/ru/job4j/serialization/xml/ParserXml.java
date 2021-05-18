package ru.job4j.serialization.xml;


import ru.job4j.serialization.json.Owner;
import ru.job4j.serialization.json.Pet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class ParserXml {
    public static void main(String[] args) throws Exception {
        Owner owner = new Owner(false, 1234, "Nikolay", new Pet("Цезарь"), "+7(111) 111-11-11", "baker street 221b");
        JAXBContext context = JAXBContext.newInstance(Owner.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(owner, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Owner fromXml = (Owner) unmarshaller.unmarshal(reader);
            System.out.println(fromXml);
        }
    }
}
