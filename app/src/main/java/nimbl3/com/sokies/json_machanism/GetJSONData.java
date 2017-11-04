package nimbl3.com.sokies.json_machanism;




import org.json.JSONObject;

import nimbl3.com.sokies.constant_class.JSON_Names;
import nimbl3.com.sokies.utils.AccountDataSet;
import nimbl3.com.sokies.utils.Response;


public class GetJSONData {


    public static AccountDataSet getLoginData(String data) {
        AccountDataSet dataSet = new AccountDataSet();
        try {
            if (data != null) {
                JSONObject object = new JSONObject(data);
                if (!object.isNull(JSON_Names.KEY_STATUS)) {
                    dataSet.setmStatus(object.getString(JSON_Names.KEY_STATUS));
                } else {
                    dataSet.setmStatus(JSON_Names.KEY_NO_DATA);
                }
                JSONObject childJsonObject = object.getJSONObject(JSON_Names.KEY_CUSTOMER);
                dataSet.setmAccountString(data);

                if (!childJsonObject.isNull(JSON_Names.KEY_FIRST_NAME)) {
                    dataSet.setmFirstName(childJsonObject.getString(JSON_Names.KEY_FIRST_NAME));
                } else {
                    dataSet.setmFirstName(JSON_Names.KEY_NO_DATA);
                }

                if (!childJsonObject.isNull(JSON_Names.KEY_EMAIL)) {
                    dataSet.setmEmailId(childJsonObject.getString(JSON_Names.KEY_EMAIL));
                } else {
                    dataSet.setmEmailId(JSON_Names.KEY_NO_DATA);
                }

                if (!childJsonObject.isNull(JSON_Names.KEY_CUSTOMER_ID)) {
                    dataSet.setmCustomerId(childJsonObject.getString(JSON_Names.KEY_CUSTOMER_ID));
                } else {
                    dataSet.setmCustomerId(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.KEY_TELEPHONE)) {
                    dataSet.setmTelePhone(childJsonObject.getString(JSON_Names.KEY_TELEPHONE));
                } else {
                    dataSet.setmTelePhone(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.KEY_CATEGORY_TYPE)) {
                    dataSet.setMcategoryType(childJsonObject.getString(JSON_Names.KEY_CATEGORY_TYPE));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.ALERT)) {
                    dataSet.setAlert(childJsonObject.getString(JSON_Names.ALERT));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.PROJECT)) {
                    dataSet.setProject(childJsonObject.getString(JSON_Names.PROJECT));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }

                if (!childJsonObject.isNull(JSON_Names.MVH_PROJECT)) {
                    dataSet.setMvh_project(childJsonObject.getString(JSON_Names.MVH_PROJECT));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.LEADS)) {
                    dataSet.setLeads(childJsonObject.getString(JSON_Names.LEADS));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.CAMPAINING)) {
                    dataSet.setCampaining(childJsonObject.getString(JSON_Names.CAMPAINING));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.TRANSACTION)) {
                    dataSet.setTransaction(childJsonObject.getString(JSON_Names.TRANSACTION));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.ACTIVITIES)) {
                    dataSet.setActivities(childJsonObject.getString(JSON_Names.ACTIVITIES));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                if (!childJsonObject.isNull(JSON_Names.CONTACT)) {
                    dataSet.setContact(childJsonObject.getString(JSON_Names.CONTACT));
                } else {
                    dataSet.setMcategoryType(JSON_Names.KEY_NO_DATA);
                }
                return dataSet;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }







    public static Response getResponse(String data) {

        try {
            Response response = new Response();
            JSONObject object = new JSONObject(data);
            if (!object.isNull(JSON_Names.KEY_STATUS)) {
                response.setmStatus(object.getInt(JSON_Names.KEY_STATUS));
            } else {
                response.setmStatus(0);
            }
            if (!object.isNull(JSON_Names.KEY_MESSAGE)) {
                response.setmMessage(object.getString(JSON_Names.KEY_MESSAGE));
            } else {
                response.setmMessage("");
            }
            return response;

        } catch (Exception e) {
            return null;
        }
    }










}
