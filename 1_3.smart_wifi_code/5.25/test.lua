local nwfs  = require "meizu.nwfs"

--error
--nwfs.fork_smart_wifi_updown("mt7628", "*", "*", "*", "*", "*")




--set repeat task test
--nwfs.fork_smart_wifi_updown("mt7628", "22", "22", "22", "23", "1,4,5,0")
--posix.sleep(2)
--nwfs.fork_smart_wifi_updown("mt7628", "12", "09", "22", "23", "1,2,3,4,5,6,0")



--stop all task
nwfs.set_smart_wifi_stop("*****")



--set non-repeat task test
--nwfs.fork_smart_wifi_updown("mt7628", "12", "06", "22", "23", "non-repeat")
--posix.sleep(1)
--nwfs.fork_smart_wifi_updown("mt7628", "22", "11", "11", "22", "non-repeat")
