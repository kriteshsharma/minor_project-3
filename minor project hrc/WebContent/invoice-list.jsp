<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

    <title>Landing Page</title>
    
    <style type="text/css">
  		<%@include file="CSS/landing.css" %>
	</style>

    <script type="text/javascript">
    function GetSelected(relativePath) {
        //Reference the Table.
        var grid = document.getElementById("invoiceTable");
        var b1 = document.querySelector(".add_btn");
        var b2 = document.querySelector(".edit_btn");
        var b3 = document.querySelector(".del_btn");

        var rows = grid.rows;
        var id;
        	for(var index=1;index<rows.length;index++)
        	{
        		var row = rows[index];
        			var checkBox = row.cells[0].childNodes[1];
        			 if (checkBox.checked) {
                                    id = row.id;
                                    break;
                                }
        			 if (checkbox.checked) {
        				 b1.disabled = true;
        				 b2.disabled = false;
        				 b3.disabled = false;
        				 break;
        			 }
        			 else{
        				 b1.disabled = false;
        				 b2.disabled = true;
        				 b3.disabled = true;
        				 break;
        			 }
        	}



        window.location = relativePath + '/edit?id=' + id ;
    }

    function GetSelectedForDelete(relativePath) {
            //Reference the Table.
            var grid = document.getElementById("invoiceTable");

            var rows = grid.rows;
            var id;
            	for(var index=1;index<rows.length;index++)
            	{
            		var row = rows[index];
            			var checkBox = row.cells[0].childNodes[1];
            			 if (checkBox.checked) {
                                        id = row.id;
                                        break;
                                    }
            	}


            alert("You'll lose your record(s) after this action. We can't recover them once you delete. Are you sure you want to permanently delete them? ")
            window.location = relativePath + '/delete?id=' + id ;
        }
    

    </script>
</head>
<body>

<div class="bg">
    <header>
    	<div class="hr_logo">
    		<%@include file="images/logo.svg" %>
    	</div>
        <div class="abc_logo">
        	<%@include file="images/Group 20399.svg" %>
        </div>
    </header>
    <div class="grid">
        <p class="grid_header">Invoice List</p>
        <div class="grid_box">
        	
        	<div class="container text-left">
                <button class="add_btn" onclick="window.location.href='<%=request.getContextPath()%>/new'">Add
                </button>

            </div>
        	
        	<div class="container text-left">
               <button onclick="javascript:GetSelected('<%=request.getContextPath()%>');"
                                  class="edit_btn">Edit
                               </button>
            </div>
        	
            <div class="container text-left">
              <button onclick="javascript:GetSelectedForDelete('<%=request.getContextPath()%>');"
                                                class="del_btn">Delete
                                             </button>

            </div>

            <div class="search-wrapper">
                <input type="text" placeholder="Search by Invoice Number" onkeyup="search()"/>
            </div>
            <table class="table" id="invoiceTable">
                <tr class="col_head">
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
                    <th> Customer name</th>
                    <th> Customer #</th>
                    <th> Invoice #</th>
                    <th> Invoice Amount</th>
                    <th> Due Date</th>
                    <th> Predicted payment date</th>
                    <th> Notes</th>
                </tr>

                <c:forEach var="invoice" items="${listInvoice}">
                    <tr id="${invoice.id}">
                        <td>
                            <input type="checkbox" class="cbox" name="row-check" value="1"/>
                        </td>
                        <td>
                            <c:out value="${invoice.customerName}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.customerID}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.invoiceID}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.amount}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.dueDate}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.predictedPaymentDate}"/>
                        </td>
                        <td>
                            <c:out value="${invoice.notes}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
                
        </div>
    </div>
    <div id="id01" class="modal">
        <form class="modal-content" action="">
            <div class="md_up"><span class="md_head">Edit Invoice</span></div>
            <div class="contain123">
                <span onclick="document.getElementById('id01').style.display='none'" class="close"
                      title="Close Modal">×</span>
                <span class="md_amt">Invoice Amount</span><input type="text" id="edt_inv_amt" name="edt_inv_amt"/>
                <br>
                <span class="md_nts">Notes</span><textarea id="edt_inv_nts" name="edt_inv_nts"></textarea>
            </div>
            <div class="md_dn">
                <button type="button1" class="edt_cncl_btn"
                        onclick="document.getElementById('id01').style.display='none'">Cancel
                </button>
                <button type="button1" class="edt_sv_btn"
                        onclick="document.getElementById('id01').style.display='none'">Save
                </button>
                <button type="button1" class="edt_rst_btn"
                        onclick="document.getElementById('id01').style.display='none'">Reset
                </button>
            </div>
        </form>
    </div>
    <div id="id02" class="modal1">
        <form class="modal-content1" action="">
            <div class="md1_up"><span class="md1_head">Add Invoice</span></div>
            <div class="contain1231">
                <span onclick="document.getElementById('id02').style.display='none'" class="close1" title="Close Modal">×</span>
                <span class="md1_nm">Customer Name <span style="color: #FF5E5E;">*</span><input type="text"
                                                                                                id="add_inv_name"
                                                                                                name="add_inv_name"/>
            <span class="md1_dd">Due Date <span style="color: #FF5E5E;">*</span></span><input type="date"
                                                                                              id="add_inv_dd"
                                                                                              name="add_inv_dd"/>
            <br>
            <span class="md1_cn">Customer No. <span style="color: #FF5E5E;">*</span></span><input type="text"
                                                                                                  id="add_inv_cn"
                                                                                                  name="add_inv_cn"/>
            <span class="md1_nts">Notes</span><textarea id="add_inv_nts" name="add_inv_nts"></textarea>
            <br>
            <span class="md1_ino">Invoice No. <span style="color: #FF5E5E;">*</span></span><input type="text"
                                                                                                  id="add_inv_ino"
                                                                                                  name="add_inv_ino"/>
            <br>
            <span class="md1_amt">Invoice Amount <span style="color: #FF5E5E;">*</span></span><input type="text"
                                                                                                     id="add_inv_amt"
                                                                                                     name="add_inv_amt"/>
                </span>
            </div>
            <div class="md1_dn">
                <button type="button1" class="add_cncl_btn"
                        onclick="document.getElementById('id02').style.display='none'">Cancel
                </button>
                <button type="button1" class="add_add_btn"
                        onclick="document.getElementById('id02').style.display='none'">Add
                </button>
                <button type="button1" class="add_clr_btn"
                        onclick="document.getElementById('id02').style.display='none'">Clear
                </button>
            </div>
        </form>
    </div>
    <div id="id03" class="modal2">
        <form class="modal-content2" action="">
            <div class="md2_up"><span class="md2_head">Delete Record(s)?</span></div>
            <div class="contain1232">
                <span onclick="document.getElementById('id03').style.display='none'" class="close2" title="Close Modal">×</span>
                <span class="dlt_cnfm">You'll lose your record(s) after this action. We can't recover <br> them once you delete.<br><br> Are you sure you want to <span
                        style="color: #FF5E5E;">permanently delete</span> them?</span>
            </div>
            <div class="md2_dn">
                <button type="button1" class="dlt_dlt_btn"
                        onclick="document.getElementById('id03').style.display='none'">Delete
                </button>
                <button type="button1" class="dlt_cncl_btn"
                        onclick="document.getElementById('id03').style.display='none'">Cancel
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
  		<%@include file="JS/landing.js" %>
</script>
</body>
</html>