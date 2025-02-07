

const PREFIX_URLS_ADDRESS = "http://localhost:8080/address";

export const ApiUrlsAddress = {

  listAllAddress : PREFIX_URLS_ADDRESS,
  createOneAddress : PREFIX_URLS_ADDRESS + "/add",
  updateOneAddress : PREFIX_URLS_ADDRESS + "/update",
  deleteOneAddress : PREFIX_URLS_ADDRESS + "/delete/",
  filterAddressesList : PREFIX_URLS_ADDRESS + "/filter"

}
