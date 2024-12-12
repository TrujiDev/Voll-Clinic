CREATE TABLE inquiries (
    id BIGINT NOT NULL AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_inquiries_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT fk_inquiries_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id)
);