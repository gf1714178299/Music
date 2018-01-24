package com.gf.musics.web.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DateTableUtil {
    public static Map getDateTableRequestDate(String aoData) {
        Map requestMap = new HashMap();
        JSONArray jsonArray = JSONArray.fromObject(aoData);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("name").equals("sEcho"))
                    requestMap.put("sEcho", jsonObject.get("value").toString());
                else if (jsonObject.get("name").equals("iDisplayStart"))
                    requestMap.put("index", Integer.valueOf(jsonObject.get("value").toString()));
                else if (jsonObject.get("name").equals("iDisplayLength"))
                    requestMap.put("length", Integer.valueOf(jsonObject.get("value").toString()));
                else if (jsonObject.get("name").equals("sSearch"))
                    requestMap.put("", jsonObject.get("value").toString());
                else if (jsonObject.get("name").equals("sStora"))
                    requestMap.put("sortId", jsonObject.get("value").toString());
                else if (jsonObject.get("name").equals("sortId")) {
                    if (!jsonObject.get("value").toString().equals("0")) {
                        requestMap.put("sortId", jsonObject.get("value").toString());
                    }
                } else if (jsonObject.get("name").equals("startTime")) {
                    if (!jsonObject.get("value").toString().equals("0") && !jsonObject.get("value").toString().equals("")) {
                        requestMap.put("startTime", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("startTime");
                    }

                } else if (jsonObject.get("name").equals("endTime")) {
                    if (!jsonObject.get("value").toString().equals("0") && !jsonObject.get("value").toString().equals("")) {
                        requestMap.put("endTime", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("endTime");
                    }

                } else if (jsonObject.get("name").equals("delFlag") && jsonObject.containsKey("value")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")) {
                        requestMap.put("delFlag", jsonObject.get("value").toString());
                    }
                } else if (jsonObject.get("name").equals("iSortCol_0")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")) {
                        requestMap.put("sortColumn", jsonObject.get("value").toString());
                    }
                }
                //以下为扩展

                else if (jsonObject.get("name").equals("belongId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")) {
                        requestMap.put("belongId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("belongId");
                    }
                } else if (jsonObject.get("name").equals("provinceId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("provinceId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("provinceId");
                    }
                } else if (jsonObject.get("name").equals("cityId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("cityId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("cityId");
                    }
                } else if (jsonObject.get("name").equals("countyId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("countyId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("countyId");
                    }
                } else if (jsonObject.get("name").equals("searchSelect")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("searchSelect", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("searchSelect");
                    }
                } else if (jsonObject.get("name").equals("searchInput")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("searchInput", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("searchInput");
                    }
                } else if (jsonObject.get("name").equals("title")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("title", "%" + jsonObject.get("value").toString() + "%");
                    } else {
                        requestMap.remove("title");
                    }
                } else if (jsonObject.get("name").equals("status")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("status", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("status");
                    }
                } else if (jsonObject.get("name").equals("moduleId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("moduleId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("moduleId");
                    }
                } else if (jsonObject.get("name").equals("isHot")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isHot", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isHot");
                    }
                } else if (jsonObject.get("name").equals("isEnabled")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isEnabled", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isEnabled");
                    }
                } else if (jsonObject.get("name").equals("belongType")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("belongType", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("belongType");
                    }
                } else if (jsonObject.get("name").equals("keyWords")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("keyWords", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("keyWords");
                    }
                } else if (jsonObject.get("name").equals("identity")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("identity", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("identity");
                    }
                } else if (jsonObject.get("name").equals("isReal")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isReal", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isReal");
                    }
                } else if (jsonObject.get("name").equals("keys")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("keys", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("keys");
                    }
                } else if (jsonObject.get("name").equals("pkId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("pkId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("pkId");
                    }

                } else if (jsonObject.get("name").equals("userId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("userId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("userId");
                    }

                } else if (jsonObject.get("name").equals("type")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("type", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("type");
                    }

                } else if (jsonObject.get("name").equals("isRecomment")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isRecomment", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isRecomment");
                    }

                } else if (jsonObject.get("name").equals("type")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("type", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("type");
                    }
                } else if (jsonObject.get("name").equals("currentState")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("currentState", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("currentState");
                    }
                } else if (jsonObject.get("name").equals("resultType")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("resultType", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("resultType");
                    }
                } else if (jsonObject.get("name").equals("labelIds")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("labelIds", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("labelIds");
                    }
                } else if (jsonObject.get("name").equals("name")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("name", "%" + jsonObject.get("value").toString() + "%");
                    } else {
                        requestMap.remove("name");
                    }
                } else if (jsonObject.get("name").equals("duty")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("duty", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("duty");
                    }
                } else if (jsonObject.get("name").equals("typeId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("typeId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("typeId");
                    }
                } else if (jsonObject.get("name").equals("levelId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("levelId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("levelId");
                    }
                } else if (jsonObject.get("name").equals("isFixation")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isFixation", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isFixation");
                    }
                } else if (jsonObject.get("name").equals("allianceId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("allianceId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("allianceId");
                    }
                } else if (jsonObject.get("name").equals("userId")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("userId", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("userId");
                    }
                } else if (jsonObject.get("name").equals("isRealName")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isRealName", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isRealName");
                    }
                } else if (jsonObject.get("name").equals("isRefund")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isRefund", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isRefund");
                    }
                } else if (jsonObject.get("name").equals("isBond")) {
                    if (!jsonObject.get("value").toString().equals("-1") && !jsonObject.get("value").toString().equals("")
                            && !jsonObject.get("value").toString().equals(null) && !jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("isBond", jsonObject.get("value").toString());
                    } else {
                        requestMap.remove("isBond");
                    }
                }
                else if (jsonObject.get("name").equals("parkingLotId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("parkingLotId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("parkingLotId");
                    }
                }
                else if (jsonObject.get("name").equals("managementCompanyId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("managementCompanyId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("managementCompanyId");
                    }
                }
                else if (jsonObject.get("name").equals("money")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("money", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("money");
                    }
                }
                else if (jsonObject.get("name").equals("state")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("state", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("state");
                    }
                }
                else if (jsonObject.get("name").equals("buyerId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("buyerId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("buyerId");
                    }
                }
                else if (jsonObject.get("name").equals("orderId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("orderId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("orderId");
                    }
                }
                else if (jsonObject.get("name").equals("chargeOrderId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("chargeOrderId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("chargeOrderId");
                    }
                }
                else if (jsonObject.get("name").equals("parkingIds")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("parkingIds", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("parkingIds");
                    }
                }
                else if (jsonObject.get("name").equals("fettlerId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("fettlerId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("fettlerId");
                    }
                }
                else if (jsonObject.get("name").equals("searchType")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("searchType", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("searchType");
                    }
                }
                else if (jsonObject.get("name").equals("parentId")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("parentId", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("parentId");
                    }
                }
                else if (jsonObject.get("name").equals("account")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("account", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("account");
                    }
                }
                else if (jsonObject.get("name").equals("musicName")) {
                    if (!jsonObject.get("value").toString().equals("-1")&&!jsonObject.get("value").toString().equals("")
                            &&!jsonObject.get("value").toString().equals(null)&&!jsonObject.get("value").toString().equals("null")) {
                        requestMap.put("musicName", jsonObject.get("value").toString());
                    }else {
                        requestMap.remove("musicName");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return requestMap;
    }
}
