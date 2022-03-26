package example.mcroservice.users.services;

import example.mcroservice.users.repositories.Users_repository;
import example.mcroservice.users.vo.Users_vo;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class User_services {
  private final Users_repository repo;

  //CONSTRUCTOR----------------------------------------------------------------
  public User_services(Users_repository repo) {
    this.repo = repo;
  }

  //SERVICES-------------------------------------------------------------------
  public void create_user(Users_vo value){
    repo.save(value);
  }
  public void update_a_user(Users_vo value){
    repo.save(value);
  }

  //Id
  public Users_vo get_user_by_id(Long value){
    return repo.find_user_by_id(value);
  }

  //Email
  public Users_vo get_user_by_email(String value){
    return repo.find_user_by_email(value);
  }

  //First name
  public Users_vo get_user_by_first_name(String value){
    return repo.find_user_by_First_name(value);
  }

  //Last name
  public Users_vo get_user_by_last_name(String value){
    return repo.find_user_by_last_name(value);
  }

  
  //COINCIDENCIAS--------------------------------------------------------------
  public Page<Users_vo> get_coincidences(
  String filter_by,
  String filter_value, 
  String order_by, 
  Direction direction, 
  int page,
  int size){
    Page<Users_vo> coincidences = null;
    Pageable pageable = PageRequest.of( //me calcula la paginacion y ordena
        page, //OJO-> si queremos la primera pag debe ser 0
        size
      ).withSort(
        Sort.by( //Me ordena dependiendo de la column y la direccion desc o asc
          direction,
          order_by
        )
      );
    

    switch (filter_by) {
      case "all":
        coincidences = repo.find_coincidences_by_all(
          filter_value, pageable
        );
        break;
      case "first_name":
        coincidences = repo.find_coincidences_by_first_name(
          filter_value, pageable
        );
        break;
      case "last_name":
        coincidences = repo.find_coincidences_by_last_name(
          filter_value, pageable
        );
        break;
      case "email":
        coincidences = repo.find_coincidences_by_email(
          filter_value, pageable
        );
        break;
    }
    
    return coincidences;
  }
  
}