package org.wiperdog.consoleservice

import org.wiperdog.wiperdoglogger.WiperdogLogger4Xwiki;

class ConsoleServiceLib {
	def static WiperdogLogger4Xwiki logger = new WiperdogLogger4Xwiki("ConsoleServiceLib")

	/**
	 * Closure to run a process
	 * @param listCmd
	 * @param dir
	 * @param waitFor
	 * @return
	 */
	def runProcClosure(listCmd,dir,waitFor){
		def output = [:]
		try{
			ProcessBuilder builder = new ProcessBuilder(listCmd);
			//builder.redirectErrorStream(true);
			builder.directory(dir);
			Process p = builder.start();
			if(waitFor){
				output['exitVal'] = p.waitFor()
			}
			InputStream procOut  = p.getInputStream();
			InputStream procErr  = p.getErrorStream();

			BufferedReader brIn = new BufferedReader(new InputStreamReader(procOut))
			BufferedReader brErr = new BufferedReader(new InputStreamReader(procErr))
			def line = null
			StringBuffer stdin = new StringBuffer()
			while((line = brIn.readLine()) != null){
				stdin.append(line + "<br/>")
			}
			StringBuffer  stderr = new StringBuffer()
			while((line = brErr.readLine()) != null){
				stderr.append(line + "<br/>")
			}

			output["in"] = stdin.toString()
			output["err"] = stderr.toString()
		}catch (Exception e){
			output["in"] = ""
			output["err"] = e.toString();
		}
		return output
	}

	/**
	 * Closure to build list commands for process on Windows
	 * @param isLocalhost
	 * @param psExec
	 * @param listCmd
	 * @param isInteractive
	 * @return
	 */
	def buildCmdList(isLocalhost,psExec,listCmd,isInteractive,params){
		assert params != null && params != [:] : "Params are null or empty!"
		if(listCmd == null){
			listCmd = []
		}
		if(!isLocalhost){
			listCmd.add(psExec)
			def hostStr = ""
			if(params['wiperdog_path'] != null){
				hostStr = params['wiperdog_path']['host'] != null ? params['wiperdog_path']['host'] : ""
			}
			if(hostStr != ""){
				listCmd.add("\\\\" + hostStr)
			}else{
				listCmd.add("")
			}
			
			if(isInteractive){
				listCmd.add("-i")
			}
			listCmd.add("-accepteula")
			listCmd.add("-u")
			def userStr = ""
			if(params['wiperdog_path'] != null){
				userStr = params['wiperdog_path']['user'] != null ? params['wiperdog_path']['user'] : ""
			}
			if(userStr != ""){
				listCmd.add('"'+ userStr +'"')
			}else{
				listCmd.add("")
			}
			
			listCmd.add("-p")
			
			def passStr = ""
			if(params['wiperdog_path'] != null){
				passStr = params['wiperdog_path']['pass'] != null ? params['wiperdog_path']['pass'] : ""
			}
			if(passStr != ""){
				listCmd.add('"'+ passStr +'"')
			}else{
				listCmd.add("")
			}
			
		}
		return listCmd
	}
}
