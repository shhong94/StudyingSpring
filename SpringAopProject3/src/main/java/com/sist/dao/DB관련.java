package com.sist.dao;

public class DB관련 {
/*
 * 사용자가 작성한 글 갯수
 * CREATE TABLE board_count3(
    name VARCHAR2(34) NOT NULL,
    hit NUMBER    
);

	사용자가 작성한 글 갯수를 자동으로 업데이트
	CREATE OR REPLACE TRIGGER board_trigger3
	AFTER INSERT ON srboard3
	FOR EACH ROW
	DECLARE
	    v_cnt NUMBER;
	BEGIN
	    SELECT COUNT(*) INTO v_cnt FROM board_count3 WHERE name = :NEW.name;
	    
	    IF(v_cnt = 0) THEN
	        INSERT INTO board_count3 VALUES(:NEW.name, 1);
	        COMMIT;
	    ELSE
	        UPDATE board_count3 SET hit = hit + 1 WHERE name = :NEW.name;
	    END IF;
	END;
/
 */
}
