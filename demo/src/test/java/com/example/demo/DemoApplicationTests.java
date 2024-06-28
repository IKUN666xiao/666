package com.example.demo;

import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import com.example.demo.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void test() {
		StudentDTO studentDTO = StudentDTO.builder().minAge(10).maxAge(20).build();

		Specification specification = (root, query, cb) -> {
			List<Predicate> predicateList = new ArrayList<>();
			if (studentDTO.getName() != null) {
				predicateList.add(cb.equal(root.get("name"), studentDTO.getName()));
			}
			if (studentDTO.getMinAge() != 0) {
				predicateList.add(cb.greaterThanOrEqualTo(root.get("age"), studentDTO.getMinAge()));
			}
			if (studentDTO.getMaxAge() != 0) {
				predicateList.add(cb.lessThanOrEqualTo(root.get("age"), studentDTO.getMaxAge()));
			}

			return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
		};

		List<Student> studentList = studentRepository.findAll(specification, Sort.by("id"));
		System.out.println(studentList.size());
	}

}