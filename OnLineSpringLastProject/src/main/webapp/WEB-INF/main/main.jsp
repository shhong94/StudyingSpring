<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
   

    <title>SIS Recipe & Food</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="../assets/css/fontawesome.css">
    <link rel="stylesheet" href="../assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="../assets/css/owl.css">
    <link rel="stylesheet" href="../assets/css/food.css">
    <link rel="stylesheet" href="../assets/css/chat.css">
    
    <style type="text/css">
    	.modal-content{
    		margin-top: 300px;
    	}
    </style>

  </head>

  <body>

    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->

    <!-- 헤더 ------------------------------------------------------------------------------------------------------------------------------------------------------ 헤더 -->
    <tiles:insertAttribute name="header"/>

    <!-- content -------------------------------------------------------------------------------------------------------------------------------------------------- content -->
    <tiles:insertAttribute name="content"/>

    
    <!-- 푸터 ------------------------------------------------------------------------------------------------------------------------------------------------ 푸터 -->
    <tiles:insertAttribute name="footer"/>


    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Additional Scripts -->
    <script src="../assets/js/custom.js"></script>
    <script src="../assets/js/owl.js"></script>
    <script src="../assets/js/slick.js"></script>
    <script src="../assets/js/isotope.js"></script>
    <script src="../assets/js/accordions.js"></script>


    <script language = "text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$('#logBtn').click(function(){
    			let id = $('#id').val();
    			if(id.trim() == ""){
    				$('#id').focus();
    				return;
    			}
    			
    			let pwd = $('#pwd').val();
    			if(pwd.trim() == ""){
    				$('#pwd').focus();
    				return;
    			}
    			
    			$.ajax({
    				type: 'post',
    				url: '../main/login.do',
    				data: {"id" : id, "pwd" : pwd},
    				success: function(res){
    					let no = res.trim();
    					if(no == 1){
    						$('#result').text("아이디가 존재하지 않습니다");
    						$('#id').val("");		// 아이디 입력창을 공백으로
    						$('#pwd').val("");		// 비밀번호 입력창을 공백으로
    						$('#id').focus();		// 아이디 입력창에 포커스
    					}
    					else if(no == 2){
    						$('#result').text("비밀번호가 틀립니다");
    						$('#pwd').val("");		// 비밀번호 입력창을 공백으로
    						$('#pwd').focus();		// 비밀번호 입력창에 포커스
    					}
    					else{
    						$('#myModal').modal("hide");
    						$('#id').val("");
    						$('#pwd').val("");
    						$('#result').text("");
    					}
    				}
    			});
    		})
    	})
    </script>
    
    
    <!-- 로그인 모달 ------------------------------------------------------------------------------------------------------------------------------ 로그인 모달 -->
    <!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	      	<h3>로그인</h3>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body">
	        <table class="table">
	        	
	        	<tr>
	        		<td width=25% class="text-right">ID</td>
	        		<td width=75%>
	        			<input type=text id="id" class="input-sm" size=15 placeholder="아이디 입력">
	        		</td>
	        	</tr>
	        	<tr>
	        		<td width=25% class="text-right">PW</td>
	        		<td width=75%>
	        			<input type=password id="pwd" class="input-sm" size=15 placeholder="비밀번호 입력">
	        		</td>
	        	</tr>
	        	<tr>
	        		<td class="text-center" colspan="2">
	        			<span style="color: red;" id="result"></span>
	        		</td>
	        	</tr>
	        </table>
	      </div>
	      <div class="modal-footer">
	      	<input type="button" class="btn btn-success" value="로그인" id="logBtn">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>

  </div>
</div>

		<div id="chat_root"></div>
		<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
	  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/react/16.13.1/umd/react.production.min.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/react-dom/16.13.1/umd/react-dom.production.min.js"></script> -->
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
	  <script src="https://unpkg.com/socket.io-client@2.3.0/dist/socket.io.js"></script>
	  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	  <script type="text/babel">
		const socket = io.connect('http://localhost:3355')
			class App extends React.Component{
       constructor(props)
       {
           super(props);
           // 채팅문자를 저장하는 변수 
			this.state = {
				logs : []
			}
       }
       componentDidMount() //$(function(){
       {
			// 서버에서 전송된 채팅문자열을 저장하는 단계(logs에 저장하고 render에서 출력)
			socket.on('chat-msg', (obj)=>{
				const log2 = this.state.logs;
				log2.push(obj)
				this.setState({logs:log2})
			})

           $('div#chat').toggleClass('active');
           var $win = $(window);
           var top = $(window).scrollTop(); // 현재 스크롤바의위치값을 반환합니다.
           /*사용자 설정 값 시작*/
           var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
           var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
           var $layer         = $('div#chat_container'); // 레이어셀렉팅
           var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
           $layer.css('position', 'absolute');
           /*사용자 설정 값 끝*/
           // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
           if (top > 0 )
             $win.scrollTop(layerTopOffset+top);
           else
             $win.scrollTop(0);
           //스크롤이벤트가 발생하면
             $(window).scroll(function(){
             var yPosition = $win.scrollTop()+300;
             if (yPosition< 0)
             {
               yPosition = $win.scrollTop()+300;
             }
             $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
           });
        }
        render(){
           return (
              <ChatMain logs={this.state.logs}/>
           )
        }
    }
    class ChatMain extends React.Component{
        render(){
			const html = this.props.logs.map((m)=>
				<div className="message-right">
					<div className="message-text">{m.message}</div>
				</div>
			)

          return(
           <div id="chat_container">
             <div id="chat" className="active">
              <div id="chat_title"><header><h1 id="chat_font">실시간 채팅</h1></header></div>
             <section className="content">
					<div className="message_content">
						{html}

					</div>
				</section>
              <ChatForm/>
             </div>
           </div>
           )
        }
    }
    class ChatForm extends React.Component{
		constructor(props){
			super(props)
			this.state = {
				message : ''
			}
		}

		// 입력한 메시지를 message에 담기
		messageChange(e){
			this.setState({message : e.target.value})
		}

		// 메시지 입력 후 엔터치면 메시지 보내고 채팅입력창 지우기
		send(e){
			if(e.key == 'Enter'){
				e.preventDefault();
				socket.emit('chat-msg', {
					message : this.state.message
				})
				this.setState({message : ''})
			}
		}
        render(){
            return (
               <form>
                <input id="input_chat" type="text" onChange={this.messageChange.bind(this)} onKeyPress={this.send(this.send.bind(this))} value={this.state.message}/>
               </form>
            )
        }
    }
    ReactDOM.render(<App/>,document.getElementById('chat_root'))
		</script>
  </body>

</html>