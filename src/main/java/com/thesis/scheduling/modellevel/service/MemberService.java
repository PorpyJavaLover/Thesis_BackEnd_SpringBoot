package com.thesis.scheduling.modellevel.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.exception.MemberException;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository repository;

	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	// GET
	public long countMember() {
		return repository.count();
	}

	public Collection<Member> findAll() {
		return repository.findAll();
	}

	public Collection<Member> findAllBySOrganizationId(Organization organizationId) {
		return repository.findAllByOrganizationId(organizationId);
	}


	public Optional<Member> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Optional<Member> findByMemberId(int mId) {
		return repository.findByMemberId(mId);
	}
	
	public boolean matchPassword(String requestPassword, String realPassword) {
		return realPassword.equals(requestPassword);
	}

	
	// SET
	public Member create(Title titleNameSelected , Organization organizSelected, String firstNameTH, String lastNameTH, 
	 String firstNameEN, String lastNameEN, String usernameRe, String passwordRe, Integer roleSelected)
			throws BaseException {

		// validate
		if (Objects.isNull(titleNameSelected)) {
			throw MemberException.createTitleNull();
		}

		if (Objects.isNull(firstNameTH)) {
			throw MemberException.createTHFirstNameNull();
		}

		if (Objects.isNull(lastNameTH)) {
			throw MemberException.createTHLastNameNull();
		}

		if (Objects.isNull(usernameRe)) {
			throw MemberException.createUsernameNull();
		}

		if (Objects.isNull(passwordRe)) {
			throw MemberException.createPasswordNull();
		}

		// verify
		if (repository.existsByUsername(usernameRe)) {
			throw MemberException.createUsernameDuplicated();
		}

		// save
		Member entity = new Member();
		entity.setTitleId(titleNameSelected);
		entity.setOrganizationId(organizSelected);
		entity.setThFirstName(firstNameTH);
		entity.setThLastName(lastNameTH);
		entity.setEnFirstName(firstNameEN);
		entity.setEnLastName(lastNameEN);
		entity.setUsername(usernameRe);
		entity.setPassword(passwordRe);
		entity.setRole(roleSelected);
		entity.setActiveStatus(false);
		return 	repository.save(entity);
	}

	public void update(Member member, Title titleNameSelected , String firstNameTH, String lastNameTH, 
	 String firstNameEN, String lastNameEN, String usernameRe, String passwordRe , Integer roleSelected , boolean activeStatusSelected)
			throws BaseException {

		member.setTitleId(titleNameSelected);
		member.setThFirstName(firstNameTH);
		member.setThLastName(lastNameTH);
		member.setEnFirstName(firstNameEN);
		member.setEnLastName(lastNameEN);
		member.setUsername(usernameRe);
		member.setPassword(passwordRe);
		member.setRole(roleSelected);
		member.setActiveStatus(activeStatusSelected);
		repository.save(member);
	}
	

	// DELETE

	public void delete(Member member){
		repository.delete(member);
	}

}
