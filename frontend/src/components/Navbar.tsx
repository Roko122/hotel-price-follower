import { Button } from '@/components/ui/button.js';
import type { JSX } from 'react';
import { LogIn } from 'lucide-react';

function Navbar(): JSX.Element {
  return (
    <header className="w-full bg-linear-to-r from-[#FFFF99] via-[#C2FFDB] to-[#2A74FD]">
      <div className="flex justify-between px-15 pt-10 pb-5">
        <h1 className="text-2xl font-bold">Hotel Price Follower</h1>
        <div>
          <Button variant="outline" size="lg">
            <span className="text-lg">Kirjaudu</span>
            <LogIn />
          </Button>
        </div>
      </div>
      <svg viewBox="0 0 1915 100" fill="white" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none">
        <path d="M670 0.0414319C419.392 2.90362 273.328 9.95218 0 35.0414V98.5414L1918 108.541V35.0414C1761.6 80.1001 1673.21 99.2938 1511.5 98.5414C1358.76 84.5811 1274.02 76.0958 1146.5 43.5414C967.377 6.72641 862.29 -0.63692 670 0.0414319Z" />
      </svg>
    </header>
  );
}

export default Navbar;
