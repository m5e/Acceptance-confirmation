package com.example.model.services;

import java.util.*;

import javax.transaction.Transactional;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.controllers.api.form.bounenkaiForm;
import com.example.model.entities.bounenkai2;
import com.example.model.repositories.BounenkaiRepository;

@Service
@Transactional
public class BounenkaiService {

	@Autowired
	BounenkaiRepository BounenkaiRepository;

	/**
	 * 全検索
	 */
	public List<bounenkai2> findAll() {
		return BounenkaiRepository.findAll(new Sort(Sort.Direction.ASC, "name2"));
	}

	/**
	 * 全検索して読みがなの1文字目でグルーピングして返す
	 */
	public Map<String, List<bounenkai2>> findAllWithGroupingFirstCharactor() {
		Map<String, List<bounenkai2>> grouping_all_members = new LinkedHashMap<String, List<bounenkai2>>();
		List<bounenkai2> all_members = this.findAll();
		all_members.forEach((member) -> {
			String firstChar = member.getName2().substring(0, 1);
			if (!grouping_all_members.containsKey(firstChar)) {
				grouping_all_members.put(firstChar, new ArrayList<bounenkai2>());
			}
			grouping_all_members.get(firstChar).add(member);
		});
		return grouping_all_members;
	}

	/**
	 * 更新対象の行を検索
	 */
	public Optional<bounenkai2> findOne(@NotBlank Long id) {
		bounenkai2 findid = BounenkaiRepository.findOne(id);
		return Optional.ofNullable(findid);
	}

	/**
	 * 出欠フラグの更新
	 */
	public bounenkai2 update(bounenkai2 bounenkai, bounenkaiForm form) {
		return BounenkaiRepository.save(bounenkai.update(form.getFlag()));
	}

	/**
	 * カウント
	 */
	public long count(String flag) {
		return BounenkaiRepository.count((root, query, cb) -> cb.equal(root.get("flag"), flag));
	}

}
