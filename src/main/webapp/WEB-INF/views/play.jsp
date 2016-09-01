<%@ include file="common/header.jspf"%>
    <div class="container" align="center">
    	<div class="jumbotron game-form">
	        <form action="/play" method="post" id="playGameForm">
		        <input type="hidden" name="user_id" value="${user_id}">
		        <input type="hidden" name="game_id" value="${game_id}">
				<fieldset class="form-group">
		            <table border="0" cellpadding="0" cellspacing="0">
		                <tr>
		                    <td colspan="3" align="center"><h2>XL Spaceship</h2></td>
		                </tr>
		                <tr>
		                    <td>Next turn: </td>
		                    <td colspan="2"><font color="blue">${user_id}</font></td>
		                </tr>
		                <tr>
		                    <td colspan="3">Shots</td>
		                </tr>
		                <tr>
		                    <td></td>
		                    <td>Rows (hexadecimal value)</td>
		                    <td>Colums (hexadecimal value)</td>
		                </tr>
		                <tr>
		                    <td>Shot 1: </td>
		                    <td><div class="col-xs-6"><input type="text" name="shot1_x" class="form-control input-xm" required="required"></div></td>
		                    <td><div class="col-xs-6"><input type="text" name="shot1_y" class="form-control input-xm" required="required"></div></td>
		                </tr>
		                <tr>
		                    <td>Shot 2: </td>
		                    <td><div class="col-xs-6"><input type="text" name="shot2_x" class="form-control" required="required"></div></td>
		                    <td><div class="col-xs-6"><input type="text" name="shot2_y" class="form-control" required="required"></div></td>
		                </tr>
		                <tr>
		                    <td>Shot 3: </td>
		                    <td><div class="col-xs-6"><input type="text" name="shot3_x" class="form-control" required="required"></div></td>
		                    <td><div class="col-xs-6"><input type="text" name="shot3_y" class="form-control" required="required"></div></td>
		                </tr>
		                <tr>
		                    <td>Shot 4: </td>
		                    <td><div class="col-xs-6"><input type="text" name="shot4_x" class="form-control" required="required"></div></td>
		                    <td><div class="col-xs-6"><input type="text" name="shot4_y" class="form-control" required="required"></div></td>
		                </tr>
		                <tr>
		                    <td>Shot 5: </td>
		                    <td><div class="col-xs-6"><input type="text" name="shot5_x" class="form-control" required="required"></div></td>
		                    <td><div class="col-xs-6"><input type="text" name="shot5_y" class="form-control" required="required"></div></td>
		                </tr>
		                <tr>
		                    <td colspan="3" align="left"><input type="submit" value="Fire" class="btn btn-success btn-block" /></td>
		                </tr>
		            </table>
				</fieldset>
	            <a href="/game/${game_id}" target="_blank" class="btn btn-primary btn-lg">Status Game</a>
	        </form>
 	   </div>
    </div>
<%@ include file="common/footer.jspf"%>
