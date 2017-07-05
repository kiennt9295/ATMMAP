# ATMMAP
1.	Xác định vị trí hiện tại

	Mô tả chức năng
Chức năng xác định vị trí hiện tại là chức năng mà hệ thống yêu cầu truy cập Internet và kết nối GPS để tìm địa điểm người dùng rồi hiển thị vị trí đó lên bản dồ, nó cho phép người dử dụng biết được vị trí của mình.
	Chi tiết chức năng
-	Đầu tiên, người dùng khởi động ứng dụng, màn hình khởi động được hiển thị, hệ thống hiển thị hộp thoại kiểm tra truy cập vị trí, tiếp theo người dùng nhấn nút Allow trên hộp thoại.
-	Hệ thống xác nhận và gọi màn hình chính hệ thống hiển thị bản đồ lên màn hình và xác định vị trí hiện tại của người dùng.
-	Nếu hệ thống chưa kết nối GPS, hệ thống sẽ hiện hộp thoại, người dùng nhấn nút OK trên hộp thoại GPS. Hệ thống sẽ thoát khỏi ứng dụng va thông báo với người dùng bật định vị GPS.
-	Người dùng nhấn nút không trên hộp thoại GPS, hệ thống gọi màn hình chính
-	Màn hình chính hiển thị bản đồ mà không hiển thị vị trí hiện tại của người dùng

2.	Tìm kiếm ATM

	Mô tả chức năng
Chức năng tìm kiếm ATM là chức năng mà hệ thống sẽ tìm kiếm các ATM theo các tiêu chí của người dùng, cho phép người sử dụng có một cái nhìn cụ thể và trực quan các ATM hiển thị trên bản đồ.



	Chi tiết chức năng
-	Người dùng nhấn chọn nút tìm kiếm  trên màn hinh chính, Form màn hình nhập yêu cầu được tạo ra. Người dùng nhập địa điểm bắt đầu, Tên ngân hàng và chọn bán kính vào Màn hình yêu cầu. 
-	Người dùng chọn nút tìm kiếm, màn hình nhập yêu cầu sẽ lấy tọa độ của vị trí bắt đầu.
-	Màn hình nhập yêu cầu sẽ trả về tọa độ của vị trí bắt đầu cho Màn hình chính, màn hình chính Kiểm tra tọa độ bắt đầu, nếu tọa độ khác null thì Màn hình chính sẽ giữ yêu cầu đến CSDL để lấy các ATM  theo các tiêu chi trên. 
-	CSDL sẽ trả về thông tin các trụ ATM về màn hình chính, đường đi và các ATM sẽ hiển thị trên màn hình chinh đúng với các tiêu chí mà người dùng đã chọn, nếu tọa độ bằng null thì hiển thị thông báo trên Màn hình chính.

3.	Tra cứu theo khu vực

	Mô tả chức năng
Chức năng tra cứu theo khu vực là chức năng mà hệ thống sẽ thực hiện lọc các ATM theo các quận tương ứng trong thành phố HCM, nó cho phép người dùng xác định được phạm vi các ATM mà mình muốn tìm kiếm.
	Chi tiết chức năng
-	Người dùng nhấn chọn nút tra cứu trên màn hình chính, form Màn hình các khu vực được tạo ra, form Màn hình các khu vực giữ yêu cầu lên cơ sở dữ liệu. CSDL sẽ kiểm tra và tra về thông tin tất cả các khu vực trong bảng DISTRIST, thông tin tất cả  các khu vực được hiển thị trên màn hình khu vực.
-	Người dùng chọn khu vực cần tìm trên Màn hình tên khu vực, form màn hình ATM được tạo ra, form màn hình ATM giữ yêu cầu lên CSDL, CSDL sẽ kiểm tra và tra về thông tin tất cả các trụ atm thuộc khu vực người dùng đã chọn.
-	Thông tin tất cả các trụ ATM được hiển thị lên màn hình ATM, người dùng lựa chọn tên atm cần tìm trên màn hình ATM, màn hình ATM sẽ giữ tọa độ của trụ atm về cho Màn hình chính, màn hình chính sẽ vẽ đường đi và hiển thị Listview chỉ đường bằng văn bảng từ vị trí của người dùng đến trụ ATM đã được chọn .

4.	Xác định đường đi có nhiều ATM

	Mô tả chức năng
Chức năng xác định đường đi có nhiều ATM là chức năng mà hệ thống sẽ tìm xem các quãng đường đi mà gần đó có nhiều trụ ATM, nó cho phép người sử dụng tìm đường đi giữa 2 địa điểm và trên đường di chuyển, người dùng có thể bắt gặp các trụ ATM.
	Chi tiết chức năng
-	Người dùng nhấn chọn nút chỉ đường trên màn hinh chính, form Màn hình các yêu cầu được tạo ra, người dùng nhập địa điểm bắt đầu và địa điểm kết thúc vào Màn hình yêu cầu.
-	Người dùng chọn nút tìm kiếm, màn hình nhập yêu cầu sẽ lấy tọa độ của vị trí bắt đầu và vị trí kết thúc, màn hình nhập yêu cầu sẽ trả về tọa độ của vị trí bắt đầu và vị trí kết thúc cho Màn hình chính.
-	Nếu tọa độ khác null thì Màn hình chính sẽ giữ yêu cầu đến CSDL để lấy các ATM  trên đoạn đường đó. CSDL sẽ trả về thông tin các trụ ATM gần đoạn đường đó.
-	Đường đi và các ATM sẽ hiển thị trên màn hình chinh, nếu tọa độ bằng null thì hiển thị thông báo trên Màn hình chính.

