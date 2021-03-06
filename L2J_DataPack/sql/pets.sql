-- ---------------------------
-- Table structure for pets
-- ---------------------------
CREATE TABLE IF NOT EXISTS pets (
  item_obj_id decimal(11) NOT NULL default 0,
  objId decimal(11) ,
  name varchar(16) ,
  level decimal(11) ,
  maxHp decimal(11) ,
  curHp decimal(18,0) ,
  maxMp decimal(11) ,
  curMp decimal(18,0) ,
  acc decimal(11) ,
  crit decimal(11) ,
  evasion decimal(11) ,
  mAtk decimal(11) ,
  mDef decimal(11) ,
  mSpd decimal(11) ,
  pAtk decimal(11) ,
  pDef decimal(11) ,
  pSpd decimal(11) ,
  str decimal(11) ,
  con decimal(11) ,
  dex decimal(11) ,
  _int decimal(11) ,
  men decimal(11) ,
  wit decimal(11) ,
  exp decimal(11) ,
  sp decimal(11) ,
  karma decimal(11) ,
  pkkills decimal(11) ,
  maxload decimal(11) ,
  fed decimal(11) ,
  max_fed decimal(11) ,
  PRIMARY KEY  (item_obj_id)
);
